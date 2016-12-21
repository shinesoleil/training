package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.domain.Customer;
import com.thoughtworks.ketsu.domain.CustomerRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Path("customers")
public class CustomersApi {

    @Context
    CustomerRepository customerRepository;

    @Context
    Routes routes;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(Map<String, Object> info) {
        if (info.getOrDefault("name", "") == "") {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (customerRepository.findByName(info.get("name").toString()).isPresent()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Customer customer = customerRepository.create(info);

        return Response.created(routes.customerUrl(customer)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> findAllCustomers() {
        List<Customer> customers = customerRepository.find();

        if (customers.size() == 0) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return customers;
    }

    @Path("{customerId}")
    public CustomerApi getCustomerApi(@PathParam("customerId") long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (!customerOptional.isPresent()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return new CustomerApi(customerOptional.get());
    }
}
