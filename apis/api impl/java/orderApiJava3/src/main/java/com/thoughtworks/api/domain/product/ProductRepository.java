package com.thoughtworks.api.domain.product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductRepository {
  void create(Map<String, Object> info);

  Optional<Product> findById(long id);

  List<Product> find();
}
