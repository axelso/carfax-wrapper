package com.carfax.service;

import com.carfax.model.Listing;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/** ContentManager */
@Component
public class ContentManager {
  Logger log = LoggerFactory.getLogger(ContentManager.class);

  private static final String URL =
      "https://helix.carfax.com/search/v2/vehicles?zip=78759&radius=50&sort=BEST&vehicleCondition=USED&noAccidents=true&personalUse=true&serviceRecords=true&bodyType=Sedan&valueBadges=GOOD,GREAT&dynamicRadius=false&urlInfo=Used-Sedans-Austin-TX_bt7_c7537&bodytypes=Sedan&priceMax=20000";

  private final RestTemplate restTemplate;

  public ContentManager(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public Optional<String> getJSONContent(final String url)
      throws JsonMappingException, JsonProcessingException {

    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

    return Optional.ofNullable(response.getBody());
  }

  public List<Listing> getBestListings(final String model)
      throws JsonMappingException, JsonProcessingException {

    final Optional<String> content = this.getJSONContent(URL);
    List<Listing> listings = new ArrayList<Listing>();

    if (content.isPresent()) {
      String body = content.get();

      ObjectMapper mapper = new ObjectMapper();
      JsonNode root = mapper.readTree(body);
      JsonNode totalPageCountTmp = root.get("totalPageCount");
      JsonNode listingsTmp = root.get("listings");

      listings.addAll(this.filterListings(listingsTmp, model));

      int totalPageCount = totalPageCountTmp.intValue();
      log.info("totalPageCount: " + totalPageCount);

      for (int i = 2; i <= totalPageCount; i++) {
        final Optional<String> tmpContent = this.getJSONContent(URL + "&page=" + i);
        String tmpBody = tmpContent.get();
        ObjectMapper tmpMapper = new ObjectMapper();
        JsonNode tmpRoot = tmpMapper.readTree(tmpBody);
        JsonNode tmpListings = tmpRoot.get("listings");

        listings.addAll(this.filterListings(tmpListings, model));
      }
    }

    return listings;
  }

  private List<Listing> filterListings(final JsonNode listings, final String model) {
    if (listings.isArray()) {
      Iterable<JsonNode> iterable = () -> listings.iterator();

      Predicate<JsonNode> byModel =
          listing -> listing.get("model").asText().toLowerCase().contains(model.toLowerCase());

      Predicate<JsonNode> minYear = listing -> listing.get("year").asInt() > 2015;
      Predicate<JsonNode> maxMileage = listing -> listing.get("mileage").asInt() < 100_000;
      Predicate<JsonNode> isMileageInRange =
          listing -> {
            var currentYear = Year.now().getValue();
            var expectedMileage = (currentYear - listing.get("year").asInt()) * 18000;
            return listing.get("mileage").asInt() < expectedMileage;
          };

      List<Listing> listingsLst =
          StreamSupport.stream(iterable.spliterator(), false)
              .filter(byModel)
              .filter(minYear)
              .filter(maxMileage)
              .filter(isMileageInRange)
              .map(mapToListing)
              .collect(Collectors.toList());
      return listingsLst;
    }
    return null;
  }

  private String calculateOutOfDoorPrice(final String currentPrice) {
    return String.valueOf((Float.valueOf(currentPrice) * (1 + 0.0625)) + 150 + 33 + 74);
  }

  Function<JsonNode, Listing> mapToListing =
      listing ->
          new Listing(
              listing.get("model").asText(),
              calculateOutOfDoorPrice(listing.get("currentPrice").asText()),
              listing.get("mileage").asText(),
              listing.get("trim").asText(),
              listing.get("vdpUrl").asText(),
              listing.get("year").asText(),
              listing.get("dealer").get("name").asText());
}
