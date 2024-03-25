package com.carfax.controller;

import com.carfax.model.Listing;
import com.carfax.service.CarfaxService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** GreetingController */
@RestController
@RequestMapping("/api/bestListings")
public class CarfaxController {

  Logger log = LoggerFactory.getLogger(CarfaxController.class);
  private final CarfaxService carfaxService;

  @Autowired
  public CarfaxController(final CarfaxService carfaxService) {
    this.carfaxService = carfaxService;
  }

  @GetMapping(value = "", produces = "application/json")
  public List<Listing> getBestListings(
      @RequestParam(value = "model", defaultValue = "corolla") String model)
      throws JsonMappingException, JsonProcessingException {

    return carfaxService.getBestListingsByModel(model);
  }
}
