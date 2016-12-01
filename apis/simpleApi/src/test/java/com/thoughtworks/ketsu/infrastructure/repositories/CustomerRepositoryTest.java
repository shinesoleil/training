package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.Customer;
import com.thoughtworks.ketsu.domain.CustomerRepository;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(DatabaseTestRunner.class)
public class CustomerRepositoryTest {
    @Inject
    CustomerRepository customerRepository;

    @Test
    public void should_create_customer_and_find_by_id() {
        Map<String, Object> customerInfo = TestHelper.customerMap(1, "Amy");

        customerRepository.create(customerInfo);

        Optional<Customer> customerOptional = customerRepository.findById(1);

        assertThat(customerOptional.isPresent(), is(true));
        assertThat(customerOptional.get().getId(), is(1L));
    }

}
