//package org.tplatform.cache;
//
//import net.sf.ehcache.Cache;
//import net.sf.ehcache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.ehcache.EhCacheCacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.tplatform.constant.GlobalConstant;
//import org.tplatform.framework.log.Logger;
//import org.tplatform.framework.util.PropertyUtil;
//import org.tplatform.framework.util.StringUtil;
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
//  public org.springframework.cache.CacheManager cacheManager() {
//    Logger.i("初始化【EhCache】缓存...");
//    CacheManager cacheManager = CacheManager.newInstance();
//    Cache cache = new Cache(GlobalConstant.KEY_CACHE_SYS, 0, false, false, 3600, 3600);
//    cacheManager.addCache(cache);
//
//    String ehCacheNames = PropertyUtil.getProInfo("config", "ehCacheNames");
//    if(StringUtil.isNotEmpty(ehCacheNames)) {
//      String[] cachesNames = ehCacheNames.split(",");
//      for(String cacheName : cachesNames)
//        cacheManager.addCache(new Cache(cacheName, 0, false, false, 3600, 3600));
//    }
//
//    return new EhCacheCacheManager(cacheManager);
//  }
//
//}
