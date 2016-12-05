package com.thoughtworks.ketsu.domain;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.infrastructure.records.Record;

import java.util.*;

public class Product implements Record{
    private long id;
    private String name;

    public Product(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        Map<String, Object> map = new HashMap<String, Object>(){{
            put("id", id);
            put("name", name);
        }};
        map.put("url", routes.productUrl(this));
        return map;
    }

    public Price createPrice(Map<String, Object> priceInfo) {
        return null;
    }
}
