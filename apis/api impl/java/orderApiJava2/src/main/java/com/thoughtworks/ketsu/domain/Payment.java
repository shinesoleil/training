package com.thoughtworks.ketsu.domain;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.infrastructure.records.Record;

import java.util.HashMap;
import java.util.Map;

public class Payment implements Record{
    private long id;
    private Order order;
    private double amount;

    public Payment(long id, Order order, double amount) {
        this.id = id;
        this.order = order;
        this.amount = amount;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        Map<String, Object> res = new HashMap();
        res.put("id", id);
        res.put("amount", amount);
        res.put("url", routes.paymentUrl(order));
        return res;
    }
}
