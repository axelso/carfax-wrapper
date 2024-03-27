package com.carfax.service;

import com.carfax.config.CacheStatisticsLogger;
import com.carfax.model.Listing;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

// @CacheConfig(cacheNames = "carfax-listings")
// @CacheConfig(cacheNames = "listings")
@Service
public class CarfaxServiceImpl implements CarfaxService {

  Logger log = LoggerFactory.getLogger(CarfaxServiceImpl.class);

  private final ContentManager contentManager;
  private final CacheStatisticsLogger cacheStatisticsLogger;

  public CarfaxServiceImpl(
      final ContentManager contentManager, final CacheStatisticsLogger cacheStatisticsLogger) {
    this.contentManager = contentManager;
    this.cacheStatisticsLogger = cacheStatisticsLogger;
  }

  // @Cacheable(value = "listings")
  // @Cacheable(cacheNames = "listings")
  @Cacheable(cacheNames = "best-listings", cacheManager = "alternateCacheManager")
  public List<Listing> getBestListingsByModel(String model)
      throws JsonMappingException, JsonProcessingException {

    log.info("Data is retrieved from helix-carfax rest api");
    cacheStatisticsLogger.logCacheStatistics("best-listings");
    return contentManager.getBestListings(model);
  }
}
