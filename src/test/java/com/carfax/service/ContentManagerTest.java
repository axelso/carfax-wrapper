// package com.carfax.service;
//
// import com.carfax.model.Listing;
// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.JsonMappingException;
// import java.util.List;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.Test;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.web.client.RestTemplate;
//
// public class ContentManagerTest {
//
//   Logger log = LoggerFactory.getLogger(ContentManagerTest.class);
//
//   private static RestTemplate restTemplate;
//
//   private static ContentManager contentManager;
//
//   @BeforeAll
//   static void setup() {
//     restTemplate = new RestTemplate();
//     contentManager = new ContentManager(restTemplate);
//   }
//
//   @Test
//   void testGetBestListings() throws JsonMappingException, JsonProcessingException {
//     String model = "coro";
//     List<Listing> listings = contentManager.getBestListings(model);
//     listings.forEach(listing -> log.info(listing.toString()));
//   }
// }
