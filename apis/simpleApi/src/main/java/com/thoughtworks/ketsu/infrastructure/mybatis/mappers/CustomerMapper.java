package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface CustomerMapper {
    void save(@Param("customer") Map<String, Object> info);

    Customer findById(@Param("id") long id);
}
