package com.thoughtworks.ketsu.api;

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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(Map<String, Object> info) {
        if (customerRepository.findById(Long.valueOf(info.getOrDefault("id", 0).toString())).isPresent()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (info.getOrDefault("name", "").equals("")) {
            throw new BadRequestException("name field is needed");
        }

        customerRepository.create(info);

        return Response.status(201).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> findAllCustomer() {
        List<Customer> customers = customerRepository.find();
        if (customers.size() == 0) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return customers;
    }

    @Path("{customerId}")
    public CustomerApi getCustomer(@PathParam("customerId") long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            return new CustomerApi(customerOptional.get());
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

    }

}
