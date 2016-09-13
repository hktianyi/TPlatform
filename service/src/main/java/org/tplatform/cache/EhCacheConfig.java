//package org.tplatform.cache;
//
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//
///**
// * Created by Tianyi on 2016/1/12.
// */
//@Configuration
//@EnableCaching
//@Profile("DEV")
//public class EhCacheConfig extends CachingConfigurerSupport {
//
//  @Bean
//  public CacheManager cacheManager() {
//
////    org.ehcache.CacheManager cacheManager
////        = CacheManagerBuilder.newCacheManagerBuilder()
////        .withCache("preConfigured", CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10)))
////        .build();
////    cacheManager.init();
////
////    Cache<Long, String> preConfigured =
////        cacheManager.getCache("preConfigured", Long.class, String.class);
////
////    Cache<Long, String> myCache = cacheManager.createCache("myCache",
////        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10)).build());
////
////    myCache.put(1L, "da one!");
////    String value = myCache.get(1L);
////
////    cacheManager.removeCache("preConfigured");
////
////    cacheManager.close();
//
//    return null;
//  }
//
//}
