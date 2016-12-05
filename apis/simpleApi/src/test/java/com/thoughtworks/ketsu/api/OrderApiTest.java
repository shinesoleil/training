package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.domain.Customer;
import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(ApiTestRunner.class)
public class OrderApiTest extends ApiSupport {

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        Optional<Customer> customerOptional = Optional.of(customer);
        when(customerRepository.findById(anyLong())).thenReturn(customerOptional);
    }

    @Test
    public void should_place_order_success() {
        when(customer.placeOrder(any())).thenReturn(new Order());
//        api层不需要关注内容
//        Map<String, Object> product1Map = TestHelper.productMap(1, 10);
//        Map<String, Object> product2Map = TestHelper.productMap(2, 50);
//        List<Map> productsMap = new ArrayList(){{
//            add(product1Map);
//            add(product2Map);
//        }};

//        Map<String, Object> orderMap = TestHelper.orderMap(1, productsMap);


        Response response = post("/customers/1/orders", new HashMap<String, Object>());

        assertThat(response.getStatus(), is(201));
    }

    @Test
    public void should_return_200_when_find_all_orders() {
        List<Order> orders = new ArrayList() {{
            add(new Order());
        }};
        when(customer.findOrders()).thenReturn(orders);

        Response response = get("customers/1/orders");

        assertThat(response.getStatus(), is(200));
        assertThat(response.readEntity(List.class).size(), is(1));
    }

    @Test
    public void should_return_404_when_orders_is_empty() {
        when(customer.findOrders()).thenReturn(new ArrayList<Order>());

        Response response = get("customers/1/orders");

        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_return_200_when_find_order_by_id() {
        when(customer.findOrderById(anyLong())).thenReturn(Optional.of(new Order()));

        Response response = get("customers/1/orders/1");

        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void should_return_404_when_find_order_by_id_not_found() {
        when(customer.findOrderById(anyLong())).thenReturn(Optional.empty());

        Response response = get("customers/1/orders/1");

        assertThat(response.getStatus(), is(404));
    }
}
