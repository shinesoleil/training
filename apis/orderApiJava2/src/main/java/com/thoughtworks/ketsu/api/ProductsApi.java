package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.domain.ProductRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Path("products")
public class ProductsApi {

    @Context
    ProductRepository productRepository;

    @Context
    Routes routes;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(Map<String, Object> info) {
        if (info.getOrDefault("name", "") == "") {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (productRepository.findByName(info.get("name").toString()).isPresent()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Product product = productRepository.create(info);

        return Response.created(routes.productUrl(product)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> findAllProducts() {
        List<Product> products = productRepository.find();

        if (products.size() == 0) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return products;
    }

    @Path("{productId}")
    public ProductApi getProductApi(@PathParam("productId") long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            return new ProductApi(productOptional.get());
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

}
