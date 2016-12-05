package com.thoughtworks.ketsu.domain;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.infrastructure.records.Record;

import java.util.HashMap;
import java.util.Map;

public class Price implements Record{
    private long id;
    private Product product;
    private double amount;

    public Price(long id, Product product, double amount) {
        this.id = id;
        this.product = product;
        this.amount = amount;
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
        Map<String, Object> res = new HashMap();
        res.put("id", id);
        res.put("product_id", product.getId());
        res.put("amount", amount);
        res.put("url", routes.priceUrl(product, this));

        return res;
    }
}
