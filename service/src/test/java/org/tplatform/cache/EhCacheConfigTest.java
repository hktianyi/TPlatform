package org.tplatform.cache;

import org.ehcache.Cache;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by tianyi on 16/9/8.
 */
public class EhCacheConfigTest {
  @Test
  public void cacheManager() throws Exception {
    org.ehcache.CacheManager cacheManager
        = CacheManagerBuilder.newCacheManagerBuilder()
        .withCache("preConfigured", CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10)))
        .build();
    cacheManager.init();

    Cache<Long, String> preConfigured =
        cacheManager.getCache("preConfigured", Long.class, String.class);

    Cache<Long, String> myCache = cacheManager.createCache("myCache",
        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10)).build());

    myCache.put(1L, "da one!");
    String value = myCache.get(1L);
    Assert.assertEquals("从缓存获取失败", "da one!", value);
    cacheManager.removeCache("preConfigured");

    cacheManager.close();
  }

}