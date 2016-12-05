package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.domain.Price;
import com.thoughtworks.ketsu.domain.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PricesApi {
    private Product product;

    public PricesApi(Product product) {
        this.product = product;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPrice(Map<String, Object> info, @Context Routes routes) {
        if (info.getOrDefault("amount", "") == "") {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Price price = product.updatePrice(info);

        return Response.created(routes.priceUrl(product, price)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Price> findAllPrices() {
        List<Price> prices = product.getPricesHistory();

        if (prices.size() == 0) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return prices;
    }

    @Path("{priceId}")
    public PriceApi findPriceById(@PathParam("priceId") long id) {
        Optional<Price> priceOptional = product.getPriceById(id);

        if (!priceOptional.isPresent()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return new PriceApi(priceOptional.get());
    }
}
