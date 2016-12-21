package com.thoughtworks.api.web;

import com.thoughtworks.api.domain.order.Order;
import com.thoughtworks.api.domain.user.User;
import com.thoughtworks.api.domain.user.UserRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Path("users/{userId}/orders")
public class OrdersApi {
  @Inject
  UserRepository userRepository;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createOrder(HashMap<String, Object> info,
                              @PathParam("userId") long userId) {
    User user = userRepository.findById(userId).get();
    user.placeOrder(info);

    Optional<Order> orderOptional = user.findOrderById(Long.valueOf(String.valueOf(info.get("id"))));

    if (orderOptional.isPresent() ) {
      return Response.status(201).build();
    } else {
      return Response.status(400).build();
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Order> findOrders(@PathParam("userId") long userId) {
    User user = userRepository.findById(userId).get();
    return user.find();
  }

  @GET
  @Path("{orderId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Order findOrderById(@PathParam("userId") long userId,
                             @PathParam("orderId") long orderId) {
    User user = userRepository.findById(userId).get();
    return user.findOrderById(orderId).orElseThrow(() -> new NotFoundException("Can not find order by id"));
  }
}
