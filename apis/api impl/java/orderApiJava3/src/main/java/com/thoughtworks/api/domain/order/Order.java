package com.thoughtworks.api.domain.order;

import com.thoughtworks.api.domain.payment.Payment;
import com.thoughtworks.api.infrastructure.mybatis.mappers.PaymentMapper;
import com.thoughtworks.api.infrastructure.records.Record;
import com.thoughtworks.api.web.jersey.Routes;

import javax.inject.Inject;
import java.util.*;


public class Order implements Record{

  @Inject
  PaymentMapper paymentMapper;

  private long id;
  private long userId;
  private String name;
  private String address;
  private String phone;
  private Date time;
  private List<OrderItem> orderItemList;


  public Order() {
  }

  public Order(long id, long userId, String name, String address, String phone, Date time, List<OrderItem> orderItemList) {
    this.id = id;
    this.userId = userId;
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.time = time;
    this.orderItemList = orderItemList;
  }

  public long getId() {
    return id;
  }

  public long getUserId() {
    return userId;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public Date getTime() {
    return time;
  }

  public List<OrderItem> getOrderItemList() {
    return orderItemList;
  }

  public void pay(Map<String, Object> info) {
    paymentMapper.save(info);
  }

  public Optional<Payment> findPaymentById(long orderId) {
    return Optional.ofNullable(paymentMapper.findByOrderId(orderId));
  }

  @Override
  public Map<String, Object> toRefJson(Routes routes) {
    return new HashMap<String, Object>() {{
      put("uri", "orders/" + getId());
      put("name", getName());
      put("address", getAddress());
      put("phone", getPhone());
      put("created_at", getTime());
    }};
  }

  @Override
  public Map<String, Object> toJson(Routes routes) {
    return new HashMap<String, Object>() {{
      put("uri", "orders/" + getId());
      put("name", getName());
      put("address", getAddress());
      put("phone", getPhone());
      put("created_at", getTime());
    }};
  }
}
