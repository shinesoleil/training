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

    public Customer(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public Order placeOrder(Map<String, Object> info) {
        return null;
    }

    public List<Order> findOrders() {
        return null;
    }

    public Optional<Order> findOrderById(long id) {
        return null;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        Map<String, Object> res = new HashMap();
        res.put("id", id);
        res.put("name", name);
        res.put("url", routes.customerUrl(this));
        return res;
    }
}
