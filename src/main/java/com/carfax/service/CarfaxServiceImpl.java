package com.carfax.service;

import com.carfax.model.Listing;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

/** CarfaxServiceImpl */
@Service
public class CarfaxServiceImpl implements CarfaxService {

  public List<Listing> getBestListingsByModel(String model) {
    Listing listing = new Listing("", "", "", "", "", "", "");

    return Arrays.asList(listing);
  }
}
