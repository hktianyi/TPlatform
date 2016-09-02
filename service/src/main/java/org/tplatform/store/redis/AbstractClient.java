package org.tplatform.store.redis;

import org.tplatform.constant.GlobalConstant;
import org.tplatform.framework.log.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.util.Slowlog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public abstract class AbstractClient {
	  //
	  private static int poolIndex = 1;
	  // 默认超时时间
	  private int defaultTimeout = 10000;
	  // Redis集群，写节点，Master
	  private JedisPool masterPool;
	  // 默认Master IP
	  private String masterHost = GlobalConstant.REDISHOST;
	  // 默认端口
	  private int masterPort = 6379;
	  // Redis集群, 读节点，Slaves
	  private Map<Integer, JedisPool> slavePools;
	  // 连接密码
	  private String password;
	  // 当前数据库
	  private int database;
	  // Redis Master Server 状态 -1 : 连接失败，0 : 未连接，1 : 连接成功
	  private int writeStatus = 0;
	  // Redis slave Server 状态 -1 : 连接失败，0 : 未连接，1 : 连接成功
	  private int readStatus = 0;
	  //
	  private int delayRetry = 1000;
	  //
	  private AliveEventListener aliveEventListener;
	  //
	  private ExecutorService executor;


	  /**
	   * 默认构造器<br/>
	   * 当应用与Redis集群为同一台服务器时使用
	 * @return
	 * @return
	 * @return
	   */
	  AbstractClient(Integer database) {
		  this(GlobalConstant.REDISHOST, 6379,database);
	  }

	  /**
	   * 指定Master实例地址端口<br/>
	   * 当应用与Redis集群为不同服务器时使用，如开发测试时
	   *
	   * @param host Master IP
	   * @param port Master PORT
	   */
	  AbstractClient(String host, int port,Integer database) {
		  this(host, port, null, database );
	  }

	  /**
	   * 指定Master实例地址端口，密码，数据库<br/>
	   * 适用一套Redis集群支撑多套应用
	   *
	   * @param host     Master IP
	   * @param port     Master PORT
	   * @param password Redis密码
	   * @param database 数据库
	   */
	  AbstractClient(String host, int port, String password, Integer database) {
	    this.masterHost = host;
	    this.masterPort = port;
	    this.password = password != null && password.isEmpty() ? null : password;
	    this.database = database == null ? 0 : database;
	    // 连接集群
	    this.connectRedis();
	    this.executor = Executors.newFixedThreadPool(2, new SubscribeThreadFactory());
	    // 检查集群是否在线
	    this.checkAlive();
	   }

	  // public methods

	  public int getDatabase() {
	    return database;
	  }

	  /**
	   * 是否可以写入状态
	   *
	   * @return 写入状态
	   */
	  public int getWriteStatus() {
	    return writeStatus;
	  }

	  /**
	   * 是否可以读入状态
	   *
	   * @return 读入状态
	   */
	  public int getReadStatus() {
	    return readStatus;
	  }

	  /**
	   * 存活事件
	   *
	   * @param aliveEventListener
	   */
	  public void setAliveEventListener(AliveEventListener aliveEventListener) {
	    this.aliveEventListener = aliveEventListener;
	  }

	  /**
	   * 获得SlavePool
	   *
	   * @return 返回slave连接池
	   */
	  public JedisPool getSlavePool() {
	    JedisPool pool = slavePools.get(poolIndex);
	    poolIndex += 1;
	    if (poolIndex > slavePools.size()) poolIndex = 1;
	    return pool;
	  }

	  /**
	   * 查看Redis数据库连接是否正常
	   *
	   * @return 返回是否正常
	   */
	  public boolean pingMaster() {
	    master(jedis -> {
        String pong = null;
        try {
          pong = jedis.ping();
        } catch (Exception ignored) {
        }
        return pong;
      });
	    return true;
	  }

	  /**
	   * 查看Redis数据库连接是否正常
	   *
	   * @return 返回是否正常
	   */
	  public boolean pingSlave() {
	    slave(jedis -> {
        String pong = null;
        try {
          pong = jedis.ping();
        } catch (Exception ignored) {
        }
        return pong;
      });
	    return true;
	  }

	  /**
	   * 返回当前数据库的 key 的数量
	   *
	   * @return 当前数据库的 key 的数量
	   */
	  public Long dbSize() {
	    Object actual = master(jedis -> jedis.dbSize());

	    return actual != null ? (Long) actual : null;
	  }

	  /**
	   * 执行一个 AOF文件 重写操作
	   *
	   * @return Status code reply
	   */
	  public String bgrewriteaof() {
	    Object actual = master(Jedis::bgrewriteaof);
	    return actual != null ? (String) actual : null;
	  }

	  /**
	   * 保存当前数据库的数据到磁盘
	   *
	   * @return Status code reply
	   */
	  public String bgsave() {
	    Object actual = master(Jedis::bgsave);
	    return actual != null ? (String) actual : null;
	  }

	  /**
	   * 将当前 Redis 实例的所有数据快照(snapshot)以 RDB 文件的形式保存到硬盘(采用同步方式，一般不用)
	   *
	   * @return Status code reply
	   */
	  public String save() {
	    Object actual = master(Jedis::save);
	    return actual != null ? (String) actual : null;
	  }

	  /**
	   * 获得最近一次 Redis 成功将数据保存到磁盘的时间
	   *
	   * @return Status code reply
	   */
	  public Long lastSave() {
	    Object actual = master(Jedis::lastsave);
	    return actual != null ? (Long) actual : null;
	  }

	  /**
	   * 将当前服务器转变为指定服务器的从属服务器(slave server)
	   *
	   * @param host 从属服务器ip
	   * @param port 从属服务器端口
	   * @return Status code reply
	   */
	  public String slaveof(final String host, final Integer port) {
	    Object actual = slave(jedis -> jedis.slaveof(host, port));
	    return actual != null ? (String) actual : null;
	  }

	  /**
	   * 关闭从属服务器的复制功能
	   *
	   * @return Status code reply
	   */
	  public String slaveofNoOne() {
	    Object actual = slave(Jedis::slaveofNoOne);
	    return actual != null ? (String) actual : null;
	  }

	  /**
	   * 清空整个 Redis 服务器的数据(删除所有数据库的所有 key ,此命令从不失败)
	   *
	   * @return Status code reply
	   */
	  public String flushAll() {
	    Object actual = master(Jedis::flushAll);
	    return actual != null ? (String) actual : null;
	  }

	  /**
	   * 清空当前数据库中的所有 key(此命令从不失败)
	   *
	   * @return Status code reply
	   */
	  public String flushDB() {
	    Object actual = master(Jedis::flushDB);
	    return actual != null ? (String) actual : null;
	  }

	  /**
	   * 停止所有客户端
	   *
	   * @return Status code reply
	   */
	  public String shutdown() {
	    Object actual = master(Jedis::shutdown);
	    return actual != null ? (String) actual : null;
	  }

	  /**
	   * 获取查询日志
	   *
	   * @return Status code reply
	   */
	  public List<Slowlog> slowlogGet() {
	    Object actual = slave(Jedis::slowlogGet);
	    return actual != null ? (List<Slowlog>) actual : null;
	  }

	  /**
	   * 获取指定数量的查询日志
	   *
	   * @param entries 日志数量
	   * @return 日志
	   */
	  public List<Slowlog> slowlogGet(final Long entries) {
	    Object actual = slave(jedis -> jedis.slowlogGet(entries));
	    return actual != null ? (List<Slowlog>) actual : null;
	  }

	  /**
	   * 查看当前日志的数量
	   *
	   * @return 当前日志的数量
	   */
	  public Long slowlogLen() {
	    Object actual = slave(Jedis::slowlogLen);
	    return actual != null ? (Long) actual : null;
	  }

	  /**
	   * 清空日志
	   *
	   * @return 清空日志
	   */
	  public byte[] slowlogReset() {
	    Object actual = master(Jedis::slowlogReset);
	    return actual != null ? (byte[]) actual : null;
	  }

	  /**
	   * 返回关于 Redis 服务器的各种信息和统计值
	   *
	   * @return Redis 服务器的各种信息和统计值
	   */
	  public String info() {
	    Object actual = master(Jedis::info);
	    return actual != null ? (String) actual : null;
	  }

	  /**
	   * 获取服务器的配置参数
	   *
	   * @param pattern 参数
	   * @return 参数表
	   */
	  public List<String> configGet(final String pattern) {
	    Object actual = slave(jedis -> jedis.configGet(pattern));
	    return actual != null ? (List<String>) actual : null;
	  }

	  /**
	   * 动态改变服务器参数（无需重启，即刻生效）
	   *
	   * @param parameter 参数名
	   * @param value     参数值
	   * @return Status code reply
	   */
	  public String configSet(final String parameter, final String value) {
	    Object actual = master(jedis -> jedis.configSet(parameter, value));
	    return actual != null ? (String) actual : null;
	  }

	  /**
	   * 重置 INFO 命令中的某些统计数据
	   *
	   * @return Status code reply
	   */
	  public String configResetstart() {
	    Object actual = master(Jedis::configResetStat);
	    return actual != null ? (String) actual : null;
	  }

	  /**
	   * Redis查询，保证只从Slave查询
	   *
	   * @param action 操作
	   * @return 返回查询结果
	   */
	  public Object slave(Action action) {
	    Object actual = null;
	    if (slaveOnline()) {
	      JedisPool pool = null;
	      Jedis jedis = null;
	      try {
	        pool = this.getSlavePool();
	        if (pool != null) {
	          jedis = pool.getResource();
	          actual = action.execute(jedis);
	        } else {
	          this.readStatus = -1;
	        }
	      } catch (JedisConnectionException e) {
	        int osize = slavePools.size();
	        initSlaves(JedisPoolUtil.createJedisPoolConfig());
	        int nsize = slavePools.size();
	        this.readStatus = slavePools.isEmpty() ? -1 : 1;
	        if (osize != nsize) {
	          Logger.e("slave instance size changed! {} to {}" + osize + nsize);
	        }
	      } finally {
	        if (pool != null) {
	          pool.returnResource(jedis);
	        }
	      }
	    }else{//slave不可用使用master
	    	actual = master(action);
	    }
	    return actual;
	  }

	  /**
	   * 更新或插入数据，只从Master写入数据<br/>
	   * 若要使用方式Jedis的select方法临时切换数据库，请使用该方法
	   *
	   * @param action 操作
	   * @return 返回操作结果
	   */
	  public Object master(Action action) {
	    Object actual = null;
	    if (masterOnline()) {
	      JedisPool pool = null;
	      Jedis jedis = null;
	      try {
	        pool = this.getMasterPool();
	        jedis = pool.getResource();
	        actual = action.execute(jedis);
	      } catch (JedisConnectionException e) {
	        this.writeStatus = -1;
	        Logger.e("redis db connection reset ! please checkAlive it !");
	      } finally {
	        if (pool != null) {
	          pool.returnResource(jedis);
	        }
	      }
	    }
	    return actual;
	  }

	  // protected methods

	  /**
	   * 获得MasterPool
	   *
	   * @return
	   */
	  protected JedisPool getMasterPool() {
	    return masterPool;
	  }

	  // private methods

	  /**
	   * master配置
	   *
	   * @param config 配置
	   */
	  private void initMaster(JedisPoolConfig config) {
	    try {
	      masterPool = new JedisPool(config, this.masterHost, this.masterPort, this.defaultTimeout, this.password, this.database);
	      Jedis jedis = masterPool.getResource();
	      jedis.ping();
	      masterPool.returnResource(jedis);
	      writeStatus = 1;
	      Logger.i("redis master connected !");
	    } catch (JedisConnectionException e) {
	      writeStatus = -1;
	      Logger.e("redis master connection reset ! please check it , {} second after reconnect !" + delayRetry);
	    }
	  }

	  /**
	   * slave配置
	   *
	   * @param config 配置
	   */
	  private void initSlaves(final JedisPoolConfig config) {
	    if (masterPool != null) {
	      slavePools = new HashMap<>();
	      master(jedis -> {
          String info = jedis.info();
          String[] lines = info.split("\r\n");
          for (String line : lines) {
            if (line.contains("slave") && line.contains("online")) {
              String[] online = line.split(":")[1].split(",");
              String host = online[0].split("=")[1];
              String port = online[1].split("=")[1];
              //2.8.19
              //slave0:ip=127.0.0.1,port=6380,state=online,offset=57,lag=1  master_repl_offset:57

            // 处理当应用与redis不在同一台服务器上的情况
            if ("127.0.0.1".equalsIgnoreCase(host)) {
              host = masterHost;
            }
            jedis.hset("redis.slave", host, port);
            JedisPool pool = null;
            Jedis slave = null;
            try {
              pool = new JedisPool(config, host, Integer.valueOf(port), defaultTimeout, password, database);
              slave = pool.getResource();
              slavePools.put(slavePools.size() + 1, pool);
            } catch (JedisConnectionException e) {
              Logger.e("slave: {}" + e.getMessage());
            } finally {
              if (pool != null && slave != null) {
                pool.returnResource(slave);
              }
            }
              }
            }
            // 更新slave节点状态
            readStatus = !slavePools.isEmpty() ? 1 : -1;
            switch (readStatus) {
              case -1:
                Logger.e("redis slave connection reset ! please check it , {} second after reconnect !" + delayRetry);
                break;
              case 1:
                Logger.i("redis slave connected!");
                break;
            }

          return readStatus;
        });
	    }
	  }


	  /**
	   * 连接Redis Cluster
	   */
	  private void connectRedis() {
	    if (writeStatus != 1) {
	      initMaster(JedisPoolUtil.createJedisPoolConfig());
	      initSlaves(JedisPoolUtil.createJedisPoolConfig());
	    }
	    if (readStatus != 1) {
	      initSlaves(JedisPoolUtil.createJedisPoolConfig());
	    }
	    // 解发事件
	    if (this.aliveEventListener != null) {
	      this.aliveEventListener.onEvents(masterOnline(), slaveOnline());
	    }
	  }

	  /**
	   * 检查集群是否存活
	   *
	   * @return
	   */
	  private void checkAlive() {
	    executor.execute(() -> {
        while (true) {
          try {
            connectRedis();
            sleep(delayRetry);
          } catch (JedisConnectionException e) {
            Logger.e("redis db connection reset ! please check it , {} second after reconnect !" + delayRetry);
            sleep(delayRetry);
          }
        }
      });
	  }

	  /**
	   * Master连接池是否在线
	   *
	   * @return 若没初化成功则返回 false, 若成功则返回 true
	   */
	  private boolean masterOnline() {
	    return this.writeStatus == 1;
	  }

	  /**
	   * Slave连接池是否在线
	   *
	   * @return 若没初化成功则返回 false, 若成功则返回 true
	   */
	  private boolean slaveOnline() {
	    return this.readStatus == 1;
	  }

	  /**
	   * 休眠指定时间
	   *
	   * @param second 休眠秒数
	   */
	  private void sleep(int second) {
	    try {
	      TimeUnit.SECONDS.sleep(second);
	    } catch (InterruptedException ignored) {
	    }
	  }

	  // inner class

	  /**
	   * Redis Cluster 存活状态发生变化事件监听器
	   */
	  public interface AliveEventListener {
	    /**
	     * Redis Cluster 存活状态发生变化事件<br/>
	     * 每十秒触发一次
	     *
	     * @param masterAlive Redis Master Server是否存活
	     * @param slaveAlive  Redis Slave Server 是否存活
	     */
	    void onEvents(boolean masterAlive, boolean slaveAlive);
	  }

}
