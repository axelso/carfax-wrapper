package com.carfax.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public class JSONContent implements Serializable {

  public JSONContent(String body) {
    this.body = body;
  }

  @Getter @Setter public String body;
}
