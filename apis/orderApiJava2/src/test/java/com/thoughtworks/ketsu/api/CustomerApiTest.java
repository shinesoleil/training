package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.domain.Customer;
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
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(ApiTestRunner.class)
public class CustomerApiTest extends ApiSupport {

    @Test
    public void should_create_customer_success() {
        when(customerRepository.findByName(anyString())).thenReturn(Optional.empty());

        when(customerRepository.create(any())).thenReturn(new Customer(1, "firstCustomer"));

        Response response = post("/customers", TestHelper.customerMap("firstCustomer"));

        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toASCIIString().contains("customers/1"), is(true));
    }

    @Test
    public void should_create_customer_failure_with_invalid_info() {
        when(customerRepository.create(any())).thenReturn(new Customer(1, "firstCustomer"));

        Map<String, Object> info = TestHelper.customerMap("firstCustomer");
        info.replace("name", "");

        Response response = post("/customers", info);

        assertThat(response.getStatus(), is(400));
    }

    @Test
    public void should_create_customer_failure_with_existing_customer() {
        when(customerRepository.findByName(anyString())).thenReturn(Optional.of(customer));
        when(customerRepository.create(any())).thenReturn(new Customer(1, "firstCustomer"));

        Response response = post("/customers", TestHelper.customerMap("firstCustomer"));

        assertThat(response.getStatus(), is(400));
    }

    @Test
    public void should_find_all_customers_failure_when_not_found() {
        when(customerRepository.find()).thenReturn(new ArrayList<Customer>());

        Response response = get("/customers");

        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_find_all_customers_success() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "firstCustomer"));
        when(customerRepository.find()).thenReturn(customers);

        Response response = get("/customers");

        assertThat(response.getStatus(), is(200));
        assertThat(response.readEntity(List.class).size(), is(1));
    }

    @Test
    public void should_find_customer_by_id_not_found() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Response response = get("/customers/1");

        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_find_customer_by_id_success() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(new Customer(1, "firstCustomer")));

        Response response = get("/customers/1");

        assertThat(response.getStatus(), is(200));
        assertThat(response.readEntity(Map.class).get("name"), is("firstCustomer"));
    }
}
