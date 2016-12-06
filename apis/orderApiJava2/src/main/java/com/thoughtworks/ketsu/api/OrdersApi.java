package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.domain.Customer;
import com.thoughtworks.ketsu.domain.Order;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
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
    public Response createOrder(Map<String, Object> info, @Context Routes routes) {
        if (info.getOrDefault("order_items", "") == "") {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Order order = customer.placeOrder(info);

        return Response.created(routes.orderUrl(customer, order)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> findOrders() {
        List<Order> orders = customer.findOrders();

        if (orders.size() == 0) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return orders;
    }

    @Path("{orderId}")
    public OrderApi getOrderApi(@PathParam("orderId") long orderId) {
        Optional<Order> orderOptional = customer.findOrderById(orderId);

        if (!orderOptional.isPresent()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return new OrderApi(orderOptional.get());
    }
}
