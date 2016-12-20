package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.domain.Customer;
import com.thoughtworks.ketsu.domain.Order;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrdersApi {
    private Customer customer;

    public OrdersApi(Customer customer) {
        this.customer = customer;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response placeOrder(Map<String, Object> orderInfo) {
        customer.placeOrder(orderInfo);

        return Response.status(201).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> findAllOrders() {
        List<Order> orders = customer.findOrders();

        if(orders.size() == 0) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return customer.findOrders();
    }

    @Path("{orderId}")
    public OrderApi getOrderApi(@PathParam("orderId") long orderId) {
        Optional<Order> orderOptional = customer.findOrderById(orderId);

        if(orderOptional.isPresent()) {
            return new OrderApi(orderOptional.get());
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
}
