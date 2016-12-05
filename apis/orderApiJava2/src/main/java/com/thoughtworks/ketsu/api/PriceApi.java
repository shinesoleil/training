package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.domain.Price;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class PriceApi {
    private Price price;

    public PriceApi(Price price) {
        this.price = price;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Price findPriceById() {
        return price;
    }

}
