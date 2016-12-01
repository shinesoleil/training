package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.Customer;
import com.thoughtworks.ketsu.domain.CustomerRepository;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.CustomerMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CustomerRepositoryImpl implements CustomerRepository {
    @Inject
    CustomerMapper customerMapper;

    @Override
    public Customer create(Map<String, Object> info) {
        customerMapper.save(info);
        return customerMapper.findById(Long.valueOf(info.getOrDefault("id", 0).toString()));
    }

    @Override
    public Optional<Customer> findById(long id) {
        return Optional.of(customerMapper.findById(id));
    }

    @Override
    public List<Customer> find() {
        return null;
    }
}
