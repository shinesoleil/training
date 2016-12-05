package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.domain.Product;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class ProductApi {
    private Product product;

    public ProductApi(Product product) {
        this.product = product;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Product findProductById() {
        return product;
    }

    @Path("prices")
    public PricesApi getPricesApi() {
        return new PricesApi(product);
    }
}
