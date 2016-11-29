package com.thoughtworks.ketsu.domain;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.infrastructure.records.Record;

import java.util.HashMap;
import java.util.Map;

public class Customer implements Record{
    private long id;
    private String name;

    public Customer(long id, String name) {
        this.id = id;
        this.name = name;
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
        return map;
    }
}
