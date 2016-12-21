package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.domain.product.Product;
import com.thoughtworks.api.infrastructure.mybatis.mappers.ProductMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductRepository implements com.thoughtworks.api.domain.product.ProductRepository {
  @Inject
  ProductMapper productMapper;

  @Override
  public void create(Map<String, Object> info) {
    productMapper.save(info);
  }

  @Override
  public Optional<Product> findById(long id) {
    return Optional.ofNullable(productMapper.findById(id));
  }

  @Override
  public List<Product> find() {
    return productMapper.find();
  }
}
