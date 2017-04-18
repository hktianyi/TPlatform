package org.tplatform.store.redis;

import org.springframework.util.SerializationUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisClient extends AbstractClient {

  private static Map<Integer, RedisClient> instances = new HashMap<>();// 单例

  /**
   * 获得取实例
   *
   * @return RedisClient
   */
  public static RedisClient getInstance() {
    int database = 0;
    RedisClient redisClient = instances.get(database);
    if (redisClient == null) {
      instances.put(database, redisClient = new RedisClient());
    }
    return redisClient;
  }


  /***************** String  类型操作    ******************/

  /**
   * 把对象以key的形式放入缓存（同名key覆盖）
   *
   * @param key key
   * @param value 值
   * @return String
   */
  public String set(String key, Object value) {
    Object actual = master(jedis -> jedis.set(key.getBytes(), SerializationUtils.serialize(value)));
    return actual != null ? actual.toString() : null;
  }

  /**
   * 把对象以key的形式放入缓存（同名key覆盖）
   *
   * @return String
   */
  public String set(String key, Object value, Date expiry) {

    Object actual = master(jedis -> {
      Date now = new Date();
      long living = (expiry.getTime() - now.getTime()) / 1000;
      byte[] valueBytes = SerializationUtils.serialize(value);
      return jedis.setex(key.getBytes(), (int) living, valueBytes);
    });
    return actual != null ? actual.toString() : null;
  }

  /**
   * 把对象以key的形式放入缓存（同名key覆盖）
   *
   * @return String
   */
  public String set(final String key, final Object value, final long living) {
    Object actual = master(jedis -> {
      byte[] valueBytes = SerializationUtils.serialize(value);
      return jedis.setex(key.getBytes(), (int) living, valueBytes);
    });
    return actual != null ? actual.toString() : null;
  }

  /**
   * 根据指定key，从缓存中获取对象
   *
   * @return Object
   */
  public Object get(String key) {
    Object actual = slave(jedis -> {
      byte[] keyBytes = key.getBytes();
      if (jedis.exists(keyBytes)) {
        return SerializationUtils.deserialize(jedis.get(keyBytes));
      }
      return null;
    });
    return actual != null ? actual : null;
  }

  /***************** 哈希表HASHMAP  类型操作    ******************/

  /**
   * 给哈希表键 key 中给定域 field 赋值 value
   *
   * @return Long
   */
  public Long hset(final String key, final String field, final String value) {
    Object actual = master(jedis -> jedis.hset(key, field, value));
    return actual != null ? Long.valueOf(actual.toString()) : null;
  }

  /**
   * 保存哈希表 key 值value
   *
   * @return String
   */
  public String hmset(final String key, final Map<String, String> value) {
    Object actual = master(jedis -> jedis.hmset(key, value));
    return actual != null ? actual.toString() : null;
  }

  /**
   * 返回哈希表 key 中给定域 field 的值
   *
   * @return String
   */
  public String hget(final String key, final String field) {
    Object actual = slave(jedis -> jedis.hget(key, field));
    return actual != null ? actual.toString() : null;
  }

  /**
   * 返回哈希表 key 中，所有的域和值
   *
   * @return Map
   */
  public Map<String, String> hgetAll(final String key) {
    Object actual = slave(jedis -> jedis.hgetAll(key));
    return actual != null ? (Map<String, String>) actual : null;
  }


  /**
   * 返回哈希表 key 中的所有域
   *
   * @return Set
   */
  public Set<String> hkeys(final String key) {
    Object actual = slave(jedis -> jedis.hkeys(key));
    return actual != null ? (Set<String>) actual : null;
  }

  /**
   * 返回哈希表 key 中所有域的值
   *
   * @return List
   */
  public List<String> hvals(final String key) {
    Object actual = slave(jedis -> jedis.hvals(key));
    return actual != null ? (List<String>) actual : null;
  }

  /**
   * 返回哈希表 key 中域的数量
   *
   * @return Long
   */
  public Long hlen(final String key) {
    Object actual = slave(jedis -> jedis.hlen(key));
    return actual != null ? Long.valueOf(actual.toString()) : null;
  }

  /**
   * 查看哈希表 key 中，给定域 field 是否存在
   *
   * @return boolean
   */
  public boolean hexists(final String key, final String field) {
    Object actual = slave(jedis -> jedis.hexists(key, field));
    return actual != null ? (Boolean) actual : false;
  }

  /**
   * 删除哈希表 key 里面的键为field的键值对
   *
   * @return Long
   */
  public Long hdel(String key, String field) {
    Object actual = master(jedis -> jedis.hdel(key, field));
    return actual != null ? Long.valueOf(actual.toString()) : null;
  }


  /*****************列表LIST 类型操作    ******************/


  /**
   * 保存字符串到队列 头部
   *
   * @return Long
   */
  public Long lpush(final String key, final String... strings) {
    Object actual = master(jedis -> jedis.lpush(key, strings));
    return actual != null ? (Long) actual : null;
  }

  /**
   * 保存字符串到队列尾部
   *
   * @return Long
   */
  public Long rpush(final String key, final String... strings) {
    Object actual = master(jedis -> jedis.rpush(key, strings));
    return actual != null ? (Long) actual : null;
  }

  /**
   * 设置list中指定下标元素的值
   *
   * @return String
   */
  public String lset(final String key, final long index, final String value) {
    Object actual = master(jedis -> jedis.lset(key, index, value));
    return actual != null ? (String) actual : null;
  }

  /**
   * 返回列表 key 中元素
   *
   * @return List
   */
  public List<String> lrange(final String key, final long start,
                             final long end) {
    Object actual = slave(jedis -> jedis.lrange(key, start, end));
    return actual != null ? (List<String>) actual : null;
  }

  /**
   * 返回列表 key 中，左边元素并删除
   * <p>
   * if the list contains the elements "a","b","c" LPOP will return "a" and the list will become "b","c".
   * If the key does not exist or the list is already empty the special value 'nil' is returned.
   *
   * @return String
   */
  public String lpop(final String key) {
    Object actual = master(jedis -> jedis.lpop(key));
    return actual != null ? (String) actual : null;
  }

  /**
   * 返回列表 key 中，右边元素并删除
   * <p>
   * if the list contains  the elements "a","b","c" RPOP will return "c" and the list will become "a","b".
   * If the key does not exist or the list is already empty the special value 'nil' is returned.
   *
   * @return String
   */
  public String rpop(final String key) {
    Object actual = master(jedis -> jedis.rpop(key));
    return actual != null ? (String) actual : null;
  }


  /**
   * 保留指定key 的值范围内的数据
   *
   * @return String
   */
  public String ltrim(String key, int start, int end) {
    Object actual = master(jedis -> jedis.ltrim(key, start, end));
    return actual != null ? (String) actual : null;
  }

  /**
   * 从key对应list中删除count个和value相同的元素。
   * count&gt;0时，按从头到尾的顺序删除
   *
   * @return Long
   */
  public Long lrem(String key, int count, String value) {
    Object actual = master(jedis -> jedis.lrem(key, count, value));
    return actual != null ? (Long) actual : null;
  }

  /**
   * 返回list 指定位置的元素
   *
   * @return String
   */
  public String lindex(final String key, final long index) {
    Object actual = slave(jedis -> jedis.lindex(key, index));
    return actual != null ? (String) actual : null;
  }

  /**
   * 返回list中元素的个数
   *
   * @return Long
   */
  public Long llen(final String key) {
    Object actual = slave(jedis -> jedis.llen(key));
    return actual != null ? (Long) actual : null;
  }


  /*****************集合 set 类型操作    ******************/

  /**
   * 给set 添加元素
   *
   * @return Long
   */
  public Long sadd(String key, String value) {
    Object actual = master(jedis -> jedis.sadd(key, value));
    return actual != null ? (Long) actual : null;
  }

  /**
   * Remove the specified member from the set value stored at key.
   *
   * @return Long
   */
  public Long srem(String key, String member) {
    Object actual = master(jedis -> jedis.srem(key, member));
    return actual != null ? (Long) actual : null;
  }

  /**
   * Return all the members (elements) of the set value stored at key
   *
   * @return Set
   */
  public Set<String> smembers(String key) {
    Object actual = slave(jedis -> jedis.smembers(key));
    return actual != null ? (Set<String>) actual : null;
  }

  /**
   * 取得SET成员总数
   *
   * @return Long
   */
  public Long scard(String key) {
    Object actual = slave(jedis -> jedis.scard(key));
    return actual != null ? (Long) actual : null;
  }

  /**
   * 集合求差集
   *
   * @return Set
   */
  public Set<String> sdiff(String... keys) {
    Object actual = slave(jedis -> jedis.sdiff(keys));
    return actual != null ? (Set<String>) actual : null;
  }

  /**
   * 集合求交集
   *
   * @return Set
   */
  public Set<String> sinter(String... keys) {
    Object actual = slave(jedis -> jedis.sinter(keys));
    return actual != null ? (Set<String>) actual : null;
  }

  /**
   * 集合求并集
   *
   * @return Set
   */
  public Set<String> sunion(String... keys) {
    Object actual = slave(jedis -> jedis.sunion(keys));
    return actual != null ? (Set<String>) actual : null;
  }

  /**
   * 集合求差集，并保存结果集到另一集合
   *
   * @return Long
   */
  public Long sdiffstore(String dstkey, String... keys) {
    Object actual = master(jedis -> jedis.sdiffstore(dstkey, keys));
    return actual != null ? (Long) actual : null;
  }

  /**
   * 集合求交集，并保存结果集到另一集合
   *
   * @return Long
   */
  public Long sunion(String dstkey, String... keys) {
    Object actual = master(jedis -> jedis.sinterstore(dstkey, keys));
    return actual != null ? (Long) actual : null;
  }

  /**
   * 集合求并集，并保存结果集到另一集合
   *
   * @return Long
   */
  public Long sunionstore(String dstkey, String... keys) {
    Object actual = master(jedis -> jedis.sunionstore(dstkey, keys));
    return actual != null ? (Long) actual : null;
  }

  /**
   * 判断给定值是否为SET成员
   *
   * @return Boolean
   */
  public Boolean sismember(String key, String member) {
    Object actual = slave(jedis -> jedis.sismember(key, member));
    return actual != null ? (Boolean) actual : null;
  }

  /**
   * 删除并返回SET任一成员
   *
   * @return String
   */
  public String spop(String key) {
    Object actual = master(jedis -> jedis.spop(key));
    return actual != null ? (String) actual : null;
  }

  /**
   * 返回SET任一成员
   *
   * @return String
   */
  public String sismember(String key) {
    Object actual = slave(jedis -> jedis.srandmember(key));
    return actual != null ? (String) actual : null;
  }

  /**
   * 将一个SET中一个成员移动到另一个SET中
   *
   * @return String
   */
  public String sismember(String srckey, String dstkey, String member) {
    Object actual = master(jedis -> jedis.smove(srckey, dstkey, member));
    return actual != null ? (String) actual : null;
  }


  /*****************  sortedSet有序集合   类型操作    ******************/

  /**
   * 在SSET中添加一个成员，或者说更新已有成员的SCORE
   *
   * @return Long
   */
  public Long zadd(String key, double score, String member) {
    Object actual = master(jedis -> jedis.zadd(key, score, member));
    return actual != null ? (Long) actual : null;
  }


  /**
   * 取得SSET的成员总数
   *
   * @return Long
   */
  public Long zcard(String key) {
    Object actual = slave(jedis -> jedis.zcard(key));
    return actual != null ? (Long) actual : null;
  }

  /**
   * 计算SSET中SCORE在一个给定范围内的成员总数
   *
   * @return Long
   */
  public Long zcount(String key, String min, String max) {
    Object actual = slave(jedis -> jedis.zcount(key, min, max));
    return actual != null ? (Long) actual : null;
  }

  /**
   * 为SSET中的成员自增SCORE
   *
   * @return Double
   */
  public Double zincrby(String key, double score, String member) {
    Object actual = master(jedis -> jedis.zincrby(key, score, member));
    return actual != null ? (Double) actual : null;
  }

  /**
   * 求SSET交集，并将结果集保存到一个新KEY
   *
   * @return Long
   */
  public Long zincrby(String dstkey, String... keys) {
    Object actual = master(jedis -> jedis.zinterstore(dstkey, keys));
    return actual != null ? (Long) actual : null;
  }

  /**
   * 返回SSET中一定INDEX范围内的成员
   *
   * @return Long
   */
  public Long zrange(String key, long start, long end) {
    Object actual = slave(jedis -> jedis.zrange(key, start, end));
    return actual != null ? (Long) actual : null;
  }

  /**
   * 返回SSET中一定SCORE范围内的成员
   *
   * @return Long
   */
  public Long zincrby(String key, String min, String max) {
    Object actual = slave(jedis -> jedis.zrangeByScore(key, min, max));
    return actual != null ? (Long) actual : null;
  }

  /**
   * 求SSET交集，并将结果集保存到一个新KEY
   *
   * @return Long
   */
  public Long zrem(String key) {
    Object actual = master(jedis -> jedis.zrem(key));
    return actual != null ? (Long) actual : null;
  }

  /**
   * 删除SSET一定INDEX范围内的成员
   *
   * @return Long
   */
  public Long zremrangeByRank(String key, long start, long end) {
    Object actual = slave(jedis -> jedis.zremrangeByRank(key, start, end));
    return actual != null ? (Long) actual : null;
  }

  /**
   * 删除SSET一定INDEX范围内的成员
   *
   * @return Long
   */
  public Long zremrangeByScore(String key, double start, double end) {
    Object actual = slave(jedis -> jedis.zremrangeByScore(key, start, end));
    return actual != null ? (Long) actual : null;
  }

  /**
   * 返回SSET中一定INDEX范围内的成员，其顺序是SCORE从高到低
   *
   * @return Long
   */
  public Long zrevrange(String key, long start, long end) {
    Object actual = slave(jedis -> jedis.zrevrange(key, start, end));
    return actual != null ? (Long) actual : null;
  }

  /**
   * 返回SSET中一定SCORE范围内的成员，其顺序是SCORE从高到低
   *
   * @return Long
   */
  public Long zrevrangeByScore(String key, String max, String min) {
    Object actual = slave(jedis -> jedis.zrevrangeByScore(key, max, min));
    return actual != null ? (Long) actual : null;
  }

  /**
   * 获得SSET中与给定MEMBER关联的SCORE
   *
   * @return Double
   */
  public Double zscore(String key, String member) {
    Object actual = slave(jedis -> jedis.zscore(key, member));
    return actual != null ? (Double) actual : null;
  }

  /**
   * SSET求并集，并将结果集存到一个新的KEY中
   *
   * @return Long
   */
  public Long zunionstore(String dstkey, String... keys) {
    Object actual = slave(jedis -> jedis.zunionstore(dstkey, keys));
    return actual != null ? (Long) actual : null;
  }


  /***************** ABOUT  KEY  ******************/

  /**
   * 删除给定的一个或多个 key
   * 不存在的 key 会被忽略
   *
   * @param key 缓存名称
   * @return 被删除 key 的数量
   */
  public Long del(final String key) {
    Object actual = master(jedis -> jedis.del(key.getBytes()));
    return actual != null ? (Long) actual : null;
  }

  /**
   * 检查给定 key 是否存在
   *
   * @return boolean
   */
  public boolean exists(final String key) {
    Object actual = slave(jedis -> jedis.exists(key.getBytes()));
    return actual != null ? (Boolean) actual : false;
  }

  /**
   * 设定一个KEy的活动时间
   *
   * @param key
   * @param liveTime TTL（second）
   * @return Long
   */
  public Long expire(String key, int liveTime) {
    Object actual = master(jedis -> jedis.expire(key, liveTime));
    return actual != null ? Long.valueOf(actual.toString()) : null;
  }

  /**
   * key 存活到一个unix时间戳时间
   * <p>
   * 1970年1月1日（UTC/GMT的午夜）开始所经过的秒数 一个小时表示为UNIX时间戳格式为：3600秒
   * TTL（unix timestamp）
   *
   * @return Long
   */
  public Long expireAt(String key, long liveTime) {
    Object actual = master(jedis -> jedis.expireAt(key, liveTime));
    return actual != null ? Long.valueOf(actual.toString()) : null;
  }


  /**
   * key 获得KEY的TTL存活
   *
   * @return Long
   */
  public Long ttl(String key) {
    Object actual = master(jedis -> jedis.ttl(key.getBytes()));
    return actual != null ? Long.valueOf(actual.toString()) : null;
  }

  /**
   * key 删除一个KEY的过期标志
   *
   * @return Long
   */
  public Long persist(String key) {
    Object actual = master(jedis -> jedis.persist(key));
    return actual != null ? Long.valueOf(actual.toString()) : null;
  }


  /**
   * 查找所有符合给定模式 pattern 的 key
   * <p>
   * <pre>
   * KEYS * 匹配数据库中所有 key 。
   * KEYS h?llo 匹配 hello ， hallo 和 hxllo 等。
   * KEYS h*llo 匹配 hllo 和 heeeeello 等。
   * KEYS h[ae]llo 匹配 hello 和 hallo ，但不匹配 hillo
   * </pre>
   *
   * @return Set
   */
  public Set<String> keys(final String pattern) {
    Object actual = slave(jedis -> jedis.keys(pattern));
    return actual != null ? (Set<String>) actual : null;
  }

  /**
   * 自增每次加1
   *
   * @return Long
   */
  public Long incr(String key) {
    Object actual = master(jedis -> jedis.incr(key));
    return actual != null ? Long.valueOf(actual.toString()) : null;
  }

  /**
   * 为KEY对应的整数值自增increment
   *
   * @return Long
   */
  public Long incrBy(String key, long increment) {
    Object actual = master(jedis -> jedis.incrBy(key, increment));
    return actual != null ? Long.valueOf(actual.toString()) : null;
  }


}
