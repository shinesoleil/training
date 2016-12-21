package com.thoughtworks.api.domain.user;

import com.thoughtworks.api.domain.order.Order;
import com.thoughtworks.api.infrastructure.mybatis.mappers.OrderMapper;
import com.thoughtworks.api.infrastructure.mybatis.mappers.ProductMapper;
import com.thoughtworks.api.infrastructure.records.Record;
import com.thoughtworks.api.web.jersey.Routes;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class User implements Record {
  @Inject
  OrderMapper orderMapper;


  @Inject
  ProductMapper productMapper;

  private long id;
  private String name;

  public User() {
  }

  public User(long id, String name) {
    this.id = id;
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public double getProductPrice(long productId) {
    return productMapper.findById(productId).getPrice();
  }

  public void placeOrder(Map<String, Object> info) {
    double amount = 0;
    long productId;
    int quantity;
    for (Map map: (List<Map>)info.get("order_items")) {
      productId =  Long.valueOf(String.valueOf(map.get("product_id")));
      quantity = Integer.valueOf(String.valueOf(map.get("quantity")));
      amount +=  getProductPrice(productId) * quantity;
      map.put("amount", amount);
    }
    orderMapper.save(info);
  }

  public Optional<Order> findOrderById(long orderId) {
    return Optional.ofNullable(orderMapper.findById(orderId));
  }

  public List<Order> find() {
    return orderMapper.find();
  }
  @Override
  public Map<String, Object> toRefJson(Routes routes) {
    return new HashMap<String, Object>() {{
      put("id", id);
      put("uri", routes.userUrl(User.this));
      put("name", name);
    }};
  }

  @Override
  public Map<String, Object> toJson(Routes routes) {
    return new HashMap<String, Object>() {{
      put("id", id);
      put("uri", routes.userUrl(User.this));
      put("name", name);
    }};
  }
}
