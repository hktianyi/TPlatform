package org.tplatform.store.redis;

import redis.clients.jedis.Jedis;

/**
 * Jedis查询接口
 * Created by Tianyi on 2015/1/7.
 */
public interface Action {
  /**
   * Redis 通用查询
   *
   * @param jedis redis客户端
   * @return
   */
  Object execute(final Jedis jedis);
}
