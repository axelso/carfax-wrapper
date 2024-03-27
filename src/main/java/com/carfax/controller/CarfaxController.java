package com.carfax.controller;

import com.carfax.model.Listing;
import com.carfax.service.CarfaxService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bestListings")
@Validated
public class CarfaxController {

  Logger log = LoggerFactory.getLogger(CarfaxController.class);
  private final CarfaxService carfaxService;

  public CarfaxController(final CarfaxService carfaxService) {
    this.carfaxService = carfaxService;
  }

  @GetMapping(value = "", produces = "application/json")
  public List<Listing> getBestListings(
      @RequestParam(value = "model")
          @NotBlank(message = "model shouldn't be empty")
          @Size(min = 1, max = 7, message = "model should be 1 to 10 chars long")
          String model)
      throws JsonMappingException, JsonProcessingException {

    return carfaxService.getBestListingsByModel(model);
  }
}
