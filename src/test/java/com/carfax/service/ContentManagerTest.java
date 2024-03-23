package com.carfax.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** ContentManagerTest */
public class ContentManagerTest {

  Logger log = LoggerFactory.getLogger(ContentManagerTest.class);

  static ContentManager contentManager;

  @BeforeAll
  static void setup() {
    contentManager = new ContentManager();
  }

  @Test
  void testGetContent() throws JsonMappingException, JsonProcessingException {
    String model = "coro";
    contentManager.getListings(model);
  }
}
