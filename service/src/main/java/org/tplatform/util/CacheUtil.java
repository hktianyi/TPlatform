package org.tplatform.util;

import org.tplatform.store.redis.RedisClient;
import org.tplatform.framework.util.StringUtil;

import java.util.Calendar;

/**
 * 缓存工具类
 */
public class CacheUtil {
  // 创建全局的唯一实例
  private static final RedisClient cache;

  static {
    //TODO 如果redis不可用，换用ehcache
    cache = RedisClient.getInstance(1);
  }

  private CacheUtil() {
  }

  /**
   * @param key：缓存的key
   * @description: 获取缓存信息
   * @return：被缓存对象
   * @Exception：空指针，memcache连接异常
   */
  public static Object find(String key) {
    return StringUtil.isNotEmpty(key) && cache.exists(key) ? cache.get(key) : null;
  }

  /**
   * @param key
   * @param value
   * @description:存入缓存对象，永不失效
   */
  public static void add(String key, Object value) {
    cache.set(key, value);
  }

  /**
   * @param key
   * @param value
   * @param living ：缓存时间
   * @description: 存入缓存对象，自定义实效日期
   */
  public static void add(String key, Object value, long living) {
    cache.set(key, value, living);
  }

  /**
   * @param key
   * @param value
   * @description: 存入缓存对象，当天有效
   */
  public static void addToday(String key, Object value) {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    cache.set(key, value, cal.getTime());
  }

  /**
   * @param key
   * @description: 删除缓存
   */
  public static void delObject(String key) {
    cache.del(key);
  }

  /**
   * @param key
   * @description: 判断该key是否在缓存中存在
   */
  public static boolean keyExists(String key) {
    return cache.exists(key);
  }

  /**
   * @param key
   * @param value
   * @description: 更新缓存中已存在对象
   */
  public static void updObject(String key, Object value) {
    if (cache.exists(key)) {
      cache.del(key);
    }
    cache.set(key, value);
  }

  /**
   * @param key:        传入对象key
   * @param value：传入对象值
   * @param living      ：失效时间
   * @description: 更新缓存中已存在对象, 带有失效时间
   */
  public static void updObject(String key, Object value, long living) {
    if (cache.exists(key)) {
      cache.del(key);
    }
    cache.set(key, value, living);
  }

  /**
   * @param key    :传入对象key
   * @param value: 传入对象值
   * @description: 更新缓存中已存在对象, 当天有效
   */
  public static void updObjectToday(String key, Object value) {
    if (cache.exists(key)) {
      cache.del(key);
    }
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    cache.set(key, value, cal.getTime());
  }
}
