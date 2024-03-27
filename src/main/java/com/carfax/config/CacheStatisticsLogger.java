package com.carfax.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class CacheStatisticsLogger {

  private final CacheManager cacheManager;

  public CacheStatisticsLogger(CacheManager cacheManager) {
    this.cacheManager = cacheManager;
  }

  public void logCacheStatistics(String cacheName) {
    Cache cache = cacheManager.getCache(cacheName);
    if (cache != null) {
      com.github.benmanes.caffeine.cache.Cache nativeCache =
          (com.github.benmanes.caffeine.cache.Cache) cache.getNativeCache();
      com.github.benmanes.caffeine.cache.stats.CacheStats stats = nativeCache.stats();
      System.out.println("Cache Statistics for " + cacheName);
      System.out.println("Hit Count: " + stats.hitCount());
      System.out.println("Miss Count: " + stats.missCount());
      System.out.println("Hit Rate: " + stats.hitRate());
      System.out.println("Miss Rate: " + stats.missRate());
      // Add more statistics as needed
    } else {
      System.out.println("Cache '" + cacheName + "' not found.");
    }
  }
}
