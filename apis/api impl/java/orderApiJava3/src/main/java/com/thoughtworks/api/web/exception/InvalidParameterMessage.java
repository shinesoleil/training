package com.thoughtworks.api.web.exception;

import com.thoughtworks.api.infrastructure.records.Record;
import com.thoughtworks.api.web.jersey.Routes;

import java.util.HashMap;
import java.util.Map;

public class InvalidParameterMessage implements Record {
  private String field;
  private String message;

  public InvalidParameterMessage(String field) {
    this.field = field;
    this.message = field + " cannot be empty";
  }

  @Override
  public Map<String, Object> toRefJson(Routes routes) {
    return new HashMap<String, Object>() {{
      put("field", field);
      put("message", message);
    }} ;
  }

  @Override
  public Map<String, Object> toJson(Routes routes) {
    return toRefJson(routes);
  }
}
