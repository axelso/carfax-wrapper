package com.carfax.service;

import com.carfax.model.Listing;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/** CarfaxServiceImpl */
@CacheConfig(cacheNames = "carfax-listings")
@Service
public class CarfaxServiceImpl implements CarfaxService {

  Logger log = LoggerFactory.getLogger(CarfaxServiceImpl.class);
  private ContentManager contentManager;

  @Autowired
  public void setContentManager(final ContentManager contentManager) {
    this.contentManager = contentManager;
  }

  @Cacheable(value = "listings")
  public List<Listing> getBestListingsByModel(String model)
      throws JsonMappingException, JsonProcessingException {

    log.info("Data is retrieved from helix-carfax rest api");
    return contentManager.getListings(model);
  }
}
