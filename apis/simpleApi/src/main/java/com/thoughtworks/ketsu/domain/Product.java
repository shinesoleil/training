package com.thoughtworks.ketsu.domain;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.infrastructure.records.Record;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Product implements Record{
    private long id;
    private String name;
    private List<Price> prices;

    public Product(long id, String name) {
        this.id = id;
        this.name = name;
        this.prices = new ArrayList<>();
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
}
