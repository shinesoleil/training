package com.thoughtworks.ketsu.domain;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.infrastructure.records.Record;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Order implements Record{
    private long id;
    private Customer customer;

    public Order(long id, Customer customer) {
        this.id = id;
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Payment paid(Map<String, Object> info) {
        return null;
    }

    public Optional<Payment> findPayment() {
        return null;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        Map<String, Object> res = new HashMap<>();
        res.put("id", id);
        res.put("customer_id", customer.getId());
        res.put("url", routes.orderUrl(customer, this));
        return res;
    }
}
