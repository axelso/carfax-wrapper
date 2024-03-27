package com.carfax.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableCaching
public class CacheConfig {

  @Bean
  @Primary
  public CacheManager cacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager("listings", "jsonContent");
    cacheManager.setCaffeine(
        Caffeine.newBuilder().initialCapacity(200).maximumSize(500).weakKeys().recordStats());
    return cacheManager;
  }

  @Bean
  public CacheManager alternateCacheManager() {
    return new ConcurrentMapCacheManager("best-listings", "json-content");
  }
}
