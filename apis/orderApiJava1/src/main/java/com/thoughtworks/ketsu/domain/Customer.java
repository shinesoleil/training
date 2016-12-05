package com.thoughtworks.ketsu.domain;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.infrastructure.records.Record;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Customer implements Record{
    private long id;
    private String name;

    public Customer() {
    }

    public Customer(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Order placeOrder(Map<String, Object> orderInfo) {
        return null;
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
        map.put("url", routes.customerUrl(this));
        return map;
    }

    public List<Order> findOrders() {
        return null;
    }

    public Optional<Order> findOrderById(long id) {
        return null;
    }

    public long getId() {
        return id;
    }
}
