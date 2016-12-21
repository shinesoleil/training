package com.thoughtworks.api.web;

import com.thoughtworks.api.domain.product.Product;
import com.thoughtworks.api.domain.product.ProductRepository;
import com.thoughtworks.api.web.exception.InvalidParameterException;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Path("products")
public class ProductsApi {

  @Context
  ProductRepository productRepository;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createProduct(HashMap<String, Object> info) {
    List<String> invalidParamList = new ArrayList<>();
    if (info.get("name") == null) {
      invalidParamList.add("name");
    }
    if (info.get("description") == null) {
      invalidParamList.add("description");
    }
    if (info.get("price") == null) {
      invalidParamList.add("price");
    }
    if (invalidParamList.size() > 0) {
      throw new InvalidParameterException(invalidParamList);
    }

    productRepository.create(info);
    long id = Long.valueOf(String.valueOf(info.get("id")));

    productRepository.findById(id);
    return Response.status(201).build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Product> findProducts() {
    return productRepository.find();
  }

  @GET
  @Path("{productId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Product findProductById(@PathParam("productId") long productId) {
    return productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Can not find the product by id"));
  }
}
