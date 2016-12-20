package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.domain.Order;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

public class PaymentApi {
    private Order order;

    public PaymentApi(Order order) {
        this.order = order;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response payForOrder(Map<String, Object> paymentInfo) {
        order.paid();
        return Response.status(201).build();
    }
}
