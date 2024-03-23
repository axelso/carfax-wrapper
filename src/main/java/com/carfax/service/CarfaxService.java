package com.carfax.service;

import com.carfax.model.Listing;
import java.util.List;

/** CarfaxService */
public interface CarfaxService {

  List<Listing> getBestListingsByModel(String model);
}
