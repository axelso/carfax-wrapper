package com.carfax.service;

import com.carfax.model.Listing;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.util.List;

/** CarfaxService */
public interface CarfaxService {

  List<Listing> getBestListingsByModel(String model)
      throws JsonMappingException, JsonProcessingException;
}
