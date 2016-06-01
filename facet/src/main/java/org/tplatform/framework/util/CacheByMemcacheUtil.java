package org.tplatform.framework.util;//package com.t.framework.util;
//
//import com.google.code.yanf4j.core.impl.StandardSocketOption;
//import com.t.framework.log.Logger;
//import net.rubyeye.xmemcached.MemcachedClient;
//import net.rubyeye.xmemcached.MemcachedClientBuilder;
//import net.rubyeye.xmemcached.XMemcachedClientBuilder;
//import net.rubyeye.xmemcached.exception.MemcachedException;
//import net.rubyeye.xmemcached.utils.AddrUtil;
//
//import java.io.IOException;
//import java.util.Calendar;
//import java.util.concurrent.TimeoutException;
//
///**
// * 缓存工具类
// */
//public class CacheByMemcacheUtil {
//  // 创建全局的唯一实例
//  private static MemcachedClient mcc = null;
//
//  private CacheByMemcacheUtil() {
//  }
//
//  // 设置与缓存服务器的连接池
//  private static MemcachedClient init() {
//    MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(PropertyUtil.getProInfo("cacheServer")));
//    builder.setConnectionPoolSize(5);
//    builder.setSocketOption(StandardSocketOption.SO_RCVBUF, 32 * 1024); // 设置接收缓存区为32K，默认16K
//    builder.setSocketOption(StandardSocketOption.SO_SNDBUF, 16 * 1024); // 设置发送缓冲区为16K，默认为8K
//    builder.setSocketOption(StandardSocketOption.TCP_NODELAY, false); // 启用nagle算法，提高吞吐量，默认关闭
//    builder.getConfiguration().setStatisticsServer(false);//客户端仍然会去统计连接是否空闲
//
//    try {
//      mcc = builder.build();
//      mcc.setMergeFactor(50);   //吞吐量,默认是150，缩小到50,提高相应时间
//      mcc.setOptimizeMergeBuffer(false);  //关闭合并buffer的优化
//      mcc.setEnableHeartBeat(false);//关闭心跳检测，减小系统开销
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    return mcc;
//  }
//
//  /**
//   * 获取实例
//   */
//  public static MemcachedClient getInstance() {
//    if (mcc == null) {
//      mcc = init();
//    }
//    return mcc;
//  }
//
//  /**
//   * @param key：缓存的key
//   * @description: 获取缓存信息
//   * @return：被缓存对象
//   * @Exception：空指针，memcache连接异常
//   */
//  public static Object find(String key) throws NullPointerException {
//    try {
//      if (StringUtil.isNotEmpty(key)) {
//        return getInstance().get(key);
//      }
//    } catch (Exception e) {
//      Logger.e(CacheByMemcacheUtil.class, "findObject", e);
//      throw new NullPointerException();
//    }
//    return null;
//  }
//
//  /**
//   * @param key
//   * @param value
//   * @description:存入缓存对象，永不失效
//   */
//  public static void add(String key, Object value) {
//    try {
//      if (StringUtil.isNotEmpty(key)) {
//        getInstance().set(key, 0, value);
//      }
//    } catch (TimeoutException e) {
//      Logger.e(CacheByMemcacheUtil.class, "add", e);
//    } catch (InterruptedException e) {
//      Logger.e(CacheByMemcacheUtil.class, "add", e);
//    } catch (MemcachedException e) {
//      Logger.e(CacheByMemcacheUtil.class, "add", e);
//    }
//  }
//
//  /**
//   * @param key
//   * @param value
//   * @param expire ：失效时间
//   * @description: 存入缓存对象，自定义实效日期
//   */
//  public static void add(String key, Object value, int expire)
//      throws NullPointerException {
//    try {
//      if (StringUtil.isNotEmpty(key)) {
//        getInstance().set(key, expire, value);
//      }
//    } catch (Exception e) {
//      Logger.e(CacheByMemcacheUtil.class, "put 存入缓存对象", e);
//      throw new NullPointerException();
//    }
//  }
//
//  /**
//   * @param key
//   * @param value
//   * @description: 存入缓存对象，当天有效
//   */
//  public static void addToday(String key, Object value) {
//    try {
//      if (StringUtil.isNotEmpty(key)) {
//        Calendar cal = Calendar.getInstance();
//        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
//        add(key, value, (int) ((cal.getTimeInMillis() - System.currentTimeMillis()) / 1000));
//      }
//    } catch (Exception e) {
//      Logger.e(CacheByMemcacheUtil.class, "put 存入缓存对象", e);
//      throw new NullPointerException();
//    }
//  }
//
//  /**
//   * @param key
//   * @description: 删除缓存
//   */
//  public static void delObject(String key) {
//    try {
//      if (StringUtil.isNotEmpty(key)) {
//        getInstance().delete(key);
//      }
//    } catch (Exception e) {
//      Logger.e(CacheByMemcacheUtil.class, "delObject", e);
//      throw new NullPointerException();
//    }
//  }
//
//  /**
//   * @param key
//   * @description: 判断该key是否在缓存中存在
//   */
//  public static boolean keyExists(String key) {
//    try {
//      if (StringUtil.isEmpty(key)) {
//        return false;
//      }
//      return find(key) != null;
//    } catch (Exception e) {
//      Logger.e(CacheByMemcacheUtil.class, "keyExists", e);
//      throw new NullPointerException();
//    }
//  }
//
//  /**
//   * @param key
//   * @param value
//   * @description: 更新缓存中已存在对象
//   */
//  public static void updObject(String key, Object value) {
//    try {
//      if (StringUtil.isEmpty(key)) {
//        return;
//      }
//      delObject(key);
//      add(key, value);
//    } catch (Exception e) {
//      Logger.e(CacheByMemcacheUtil.class, "updObject", e);
//      throw new NullPointerException();
//    }
//  }
//
//  /**
//   * @param key:        传入对象key
//   * @param value：传入对象值
//   * @param expire      ：失效时间
//   * @description: 更新缓存中已存在对象, 带有失效时间
//   */
//  public static void updObject(String key, Object value, int expire)
//      throws NullPointerException {
//    try {
//      if (StringUtil.isEmpty(key)) {
//        return;
//      }
//      delObject(key);
//      add(key, value, expire);
//    } catch (Exception e) {
//      Logger.e(CacheByMemcacheUtil.class, "updObject", e);
//      throw new NullPointerException();
//    }
//  }
//
//  /**
//   * @param key    :传入对象key
//   * @param value: 传入对象值
//   * @description: 更新缓存中已存在对象, 当天有效
//   */
//  public static void updObjectToday(String key, Object value) {
//    try {
//      if (StringUtil.isEmpty(key)) {
//        return;
//      }
//      Calendar cal = Calendar.getInstance();
//      cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
//      delObject(key);
//      updObject(key, value, (int) ((cal.getTimeInMillis() - System.currentTimeMillis()) / 1000));
//    } catch (Exception e) {
//      Logger.e(CacheByMemcacheUtil.class, "updObject", e);
//      throw new NullPointerException();
//    }
//  }
////
////  /**
////   * @description: 获取批量缓存对象，以字符串数组方式返回
////   * @return：Object[]
////   * @Exception：空指针，memcache连接异常
////   */
////  public static Object[] findArrayObjects(String keys[]) {
////    try {
////      return getInstance().getMultiArray(keys);
////    } catch (Exception e) {
////      Logger.e(CacheUtil.class, "findArrayObjects", e);
////      throw new NullPointerException();
////    }
////  }
////
////  /**
////   * @description: 获取批量缓存对象，以map键值对方式返回
////   * @return：Map<String, Object>
////   */
////  public static Map<String, Object> findMapObjects(String keys[])
////      throws NullPointerException {
////    try {
////      return getInstance().getMulti(keys);
////    } catch (Exception e) {
////      Logger.e(CacheUtil.class, "findMapObjects", e);
////      throw new NullPointerException();
////    }
////  }
//
//}
