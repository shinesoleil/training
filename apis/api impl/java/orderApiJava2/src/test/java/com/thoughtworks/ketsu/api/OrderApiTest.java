package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.Payment;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

@RunWith(ApiTestRunner.class)
public class OrderApiTest extends ApiSupport {

    @Test
    public void should_create_order_success() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customer.getId()).thenReturn(1L);
        when(customer.placeOrder(any())).thenReturn(order);
        when(order.getId()).thenReturn(1L);

        Response response = post("customers/1/orders", TestHelper.orderMap());

        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toASCIIString().contains("customers/1/orders"), is(true));
    }

    @Test
    public void should_create_order_failure_with_invalid_info() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customer.getId()).thenReturn(1L);

        Map<String, Object> info = TestHelper.orderMap();
        info.replace("order_items", "");

        Response response = post("customers/1/orders", info);

        assertThat(response.getStatus(), is(400));
    }

    @Test
    public void should_find_all_orders_failure_when_not_found() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customer.findOrders()).thenReturn(new ArrayList<Order>());

        Response response = get("customers/1/orders");

        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_find_all_orders_success() {
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customer.findOrders()).thenReturn(orders);

        Response response = get("customers/1/orders");

        assertThat(response.getStatus(), is(200));
        assertThat(response.readEntity(List.class).size(), is(1));
    }

    @Test
    public void should_find_order_by_id_failure_when_not_found() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customer.findOrderById(anyLong())).thenReturn(Optional.empty());

        Response response = get("customers/1/orders/1");

        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_find_order_by_id_success() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        //返回map的不能用mock
        when(customer.findOrderById(anyLong())).thenReturn(Optional.of(new Order(1, customer)));
        when(customer.getId()).thenReturn(1L);

        Response response = get("customers/1/orders/1");

        assertThat(response.getStatus(), is(200));
        assertThat(response.readEntity(Map.class).get("id"), is(1));
    }

    @Test
    public void should_pay_for_order_success() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customer.getId()).thenReturn(1L);
        when(customer.findOrderById(anyLong())).thenReturn(Optional.of(order));
        when(order.paid(anyMap())).thenReturn(new Payment(1, order, 1000));
        when(order.getCustomer()).thenReturn(customer);
        when(order.getId()).thenReturn(1L);

        Response response = post("customers/1/orders/1/payment", TestHelper.paymentMap(1000));

        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toASCIIString().contains("customers/1/orders/1/payment"), is(true));
    }

    @Test
    public void should_find_payment_of_order_failure_when_not_found() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customer.getId()).thenReturn(1L);
        when(customer.findOrderById(anyLong())).thenReturn(Optional.of(order));
        when(order.findPayment()).thenReturn(Optional.empty());

        Response response = get("customers/1/orders/1/payment");

        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_find_payment_of_order_failure() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customer.getId()).thenReturn(1L);
        when(customer.findOrderById(anyLong())).thenReturn(Optional.of(order));
        when(order.getCustomer()).thenReturn(customer);
        when(order.getId()).thenReturn(1L);
        when(order.findPayment()).thenReturn(Optional.of(new Payment(1, order, 1000)));

        Response response = get("customers/1/orders/1/payment");

        assertThat(response.getStatus(), is(200));
        assertThat(response.readEntity(Map.class).get("amount"), is(1000D));
    }
}
