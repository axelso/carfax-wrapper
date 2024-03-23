package com.carfax.controller;

import com.carfax.model.Listing;
import com.carfax.service.CarfaxService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** GreetingController */
@RestController
@RequestMapping("carfax-wrapper")
public class CarfaxController {

  private final CarfaxService carfaxService;

  @Autowired
  public CarfaxController(final CarfaxService carfaxService) {
    this.carfaxService = carfaxService;
  }

  @GetMapping(value = "/listings", produces = "application/json")
  public List<Listing> listings(
      @RequestParam(value = "model", defaultValue = "corolla") String model) {

    return carfaxService.getBestListingsByModel(model);
  }
}
