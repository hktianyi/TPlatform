package org.tplatform.cache;

import org.apache.commons.codec.binary.Base64;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.tplatform.domain.ConfigService;
import org.tplatform.util.Logger;
import org.tplatform.util.PropertyUtil;
import org.tplatform.util.SpringContextUtil;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Tianyi on 2016/1/12.
 */
@Configuration
@EnableCaching
@EnableRedisHttpSession
@Profile({"PROD", "TEST"})
@DependsOn("springContextUtil")
public class RedisCacheConfig extends CachingConfigurerSupport {

  private static final StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

  @Bean
  public JedisConnectionFactory redisConnectionFactory() {

    String[] redisInfo = new String(Base64.decodeBase64(SpringContextUtil.getBean(ConfigService.class).getByKey("redisInfo"))).split("\\|");

    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
    // Defaults
    jedisConnectionFactory.setHostName(redisInfo[0]);
    jedisConnectionFactory.setPort(Integer.parseInt(redisInfo[1]));
    jedisConnectionFactory.setPassword(new String(Base64.decodeBase64(redisInfo[2])));
    jedisConnectionFactory.setDatabase(Integer.valueOf(redisInfo[3]));

    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    jedisPoolConfig.setMaxIdle(Integer.valueOf(PropertyUtil.getProInfo("redis.maxIdle")));
    jedisPoolConfig.setMaxWaitMillis(Integer.valueOf(PropertyUtil.getProInfo("redis.maxWaitMillis")));
    jedisPoolConfig.setTestOnBorrow(true);
    jedisConnectionFactory.setPoolConfig(jedisPoolConfig);

    return jedisConnectionFactory;
  }

  @Bean
  public CacheManager cacheManager() {
    Logger.i("初始化【Redis】缓存...");
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory());
    redisTemplate.setKeySerializer(stringRedisSerializer);
    redisTemplate.setHashKeySerializer(stringRedisSerializer);

    redisTemplate.afterPropertiesSet();

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
