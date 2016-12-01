package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(ApiTestRunner.class)
public class PaymentApiTest extends ApiSupport {

    @Test
    public void should_pay_for_order() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customer.findOrderById(anyLong())).thenReturn(Optional.of(order));
        when(order.paid()).thenReturn(true);

        Response response = post("customers/1/orders/1/payment", new HashMap<String, Object>());

        assertThat(response.getStatus(), is(201));
    }

    @Test
    public void should_check_order_payment() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customer.findOrderById(anyLong())).thenReturn(Optional.of(order));
        when(order.paid()).thenReturn(true);

        Response response = post("customers/1/orders/1/payment", new HashMap<String, Object>());

        assertThat(response.getStatus(), is(201));
    }
}
