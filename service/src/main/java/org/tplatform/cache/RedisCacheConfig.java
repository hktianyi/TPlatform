package org.tplatform.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.tplatform.util.PropertyUtil;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Tianyi on 2016/1/12.
 */
//@Configuration
//@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {

  private static final StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

  @Bean
  public JedisConnectionFactory redisConnectionFactory() {

    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
    // Defaults
    jedisConnectionFactory.setHostName(PropertyUtil.getProInfo("config", "redis.ip"));
    jedisConnectionFactory.setPort(Integer.valueOf(PropertyUtil.getProInfo("config", "redis.port")));
    jedisConnectionFactory.setPassword(PropertyUtil.getProInfo("config", "redis.password"));

    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    jedisPoolConfig.setMaxIdle(Integer.valueOf(PropertyUtil.getProInfo("config", "redis.maxIdle")));
    jedisPoolConfig.setMaxWaitMillis(Integer.valueOf(PropertyUtil.getProInfo("config", "redis.maxWaitMillis")));
    jedisPoolConfig.setTestOnBorrow(true);
    jedisConnectionFactory.setPoolConfig(jedisPoolConfig);

    return jedisConnectionFactory;
  }

  @Bean
  public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(cf);
    redisTemplate.setKeySerializer(stringRedisSerializer);
    redisTemplate.setHashKeySerializer(stringRedisSerializer);
    return redisTemplate;
  }

  @Bean
  public CacheManager cacheManager(RedisTemplate redisTemplate) {
    RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);

    // Number of seconds before expiration. Defaults to unlimited (0)
    cacheManager.setDefaultExpiration(3000); // Sets the default expire time (in seconds)
    return cacheManager;
  }

  @Bean
  public KeyGenerator keyGenerator() {
    return (target, method, params) -> {
      if (params.length == 0) {
        return SimpleKey.EMPTY;
      }
      if (params.length == 1) {
        Object param = params[0];
        if (param != null && !param.getClass().isArray()) {
          return param;
        }
      }
      return new SimpleKey(params);
    };
  }

}
