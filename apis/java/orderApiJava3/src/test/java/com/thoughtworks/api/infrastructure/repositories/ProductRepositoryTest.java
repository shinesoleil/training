package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.domain.product.Product;
import com.thoughtworks.api.support.DatabaseTestRunner;
import com.thoughtworks.api.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(DatabaseTestRunner.class)
public class ProductRepositoryTest {

  @Inject
  ProductRepository productRepository;

  @Test
  public void should_create_product_and_find_product_by_id() {
    Map<String, Object> info = TestHelper.productMap();

    productRepository.create(info);
    long id = Long.valueOf(String.valueOf(info.get("id")));
    Optional<Product> product = productRepository.findById(id);

    assertThat(product.get().getId(), is(id));
  }

  @Test
  public void should_find_all_products() {
    Map<String, Object> info = TestHelper.productMap();
    productRepository.create(info);

    long id = Long.valueOf(String.valueOf(info.get("id")));
    List<Product> productList = productRepository.find();

    assertThat(productList.size(), is(1));
    assertThat(productList.get(0).getId(), is(id));
  }
}
