package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DatabaseTestRunner.class)
public class OrderManipulationTest {

    @Test
    public void should_create_and_find_order() {
//        Map<String, Object> product1Map = TestHelper.productMap(1, 10);
//        Map<String, Object> product2Map = TestHelper.productMap(2, 50);
//        List<Map> productsMap = new ArrayList(){{
//            add(product1Map);
//            add(product2Map);
//        }};
//        Map<String, Object> orderMap = TestHelper.orderMap(1, productsMap);
//
//        Customer customer = new Customer(1, "Amy");
//
//        customer.placeOrder(orderMap);
//
//        Optional<Order> customerOptional = customer.findOrderById(1);
//
//        assertThat(customerOptional.isPresent(), is(true));
//        assertThat(customerOptional.get().getId(), is(1));
    }
}
