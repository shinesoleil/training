package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.domain.Customer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class CustomerApi {
    private Customer customer;

    public CustomerApi(Customer customer) {
        this.customer = customer;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Customer findCustomerById() {
        return customer;
    }

    @Path("orders")
    public OrdersApi getOrdersApi() {
        return new OrdersApi(customer);
    }

}
