package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.Payment;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.Optional;

public class PaymentApi {
    private Order order;

    public PaymentApi(Order order) {
        this.order = order;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response payForOrder(Map<String, Object> info, @Context Routes routes) {
        Payment payment = order.paid(info);

        return Response.created(routes.paymentUrl(order)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Payment findPaymentOfOrder() {
        Optional<Payment> paymentOptional = order.findPayment();

        if (!paymentOptional.isPresent()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return paymentOptional.get();
    }
}
