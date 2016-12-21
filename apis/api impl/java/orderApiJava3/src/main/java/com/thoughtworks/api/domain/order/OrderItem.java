package com.thoughtworks.api.domain.order;

import com.thoughtworks.api.infrastructure.records.Record;
import com.thoughtworks.api.web.jersey.Routes;

import java.util.Map;

public class OrderItem implements Record{
  private long orderId;
  private long productId;
  private int quantity;
  private double amount;

  public OrderItem() {
  }

  public OrderItem(long orderId, long productId, int quantity, double amount) {
    this.orderId = orderId;
    this.productId = productId;
    this.quantity = quantity;
    this.amount = amount;
  }

  public long getOrderId() {
    return orderId;
  }

  public long getProductId() {
    return productId;
  }

  public int getQuantity() {
    return quantity;
  }

  public double getAmount() {
    return amount;
  }

  @Override
  public Map<String, Object> toRefJson(Routes routes) {
    return null;
  }

  @Override
  public Map<String, Object> toJson(Routes routes) {
    return null;
  }
}
