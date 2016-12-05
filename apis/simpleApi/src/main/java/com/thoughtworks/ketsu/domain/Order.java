package com.thoughtworks.ketsu.domain;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.infrastructure.records.Record;

import java.util.Map;

public class Order implements Record{
    private long id;

    public Order() {
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return null;
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return null;
    }

    public boolean paid() {
        return false;
    }

    public long getId() {
        return id;
    }
}
