package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.domain.Price;
import com.thoughtworks.ketsu.domain.Product;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

public class PricesApi {
    private Product product;

    public PricesApi(Product product) {
        this.product = product;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    //context cannot be outside here. Why ???
    public Response should_create_price_by_id(Map<String, Object> info, @Context Routes routes) {
        if (info.getOrDefault("id", "") == "") {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        if (info.getOrDefault("price", "") == "") {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Price newPrice = product.createPrice(info);

        return Response.created(routes.priceUrl(product, newPrice)).build();
    }
}
