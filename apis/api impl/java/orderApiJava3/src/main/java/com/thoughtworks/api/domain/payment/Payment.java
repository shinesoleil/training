package com.thoughtworks.api.domain.payment;

import com.thoughtworks.api.infrastructure.records.Record;
import com.thoughtworks.api.web.jersey.Routes;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Payment implements Record {


  private long orderId;
  private String payType;
  private Date payTime;
  private double amount;

  public Payment() {
  }

  public Payment(long orderId, String payType, Date payTime, double amount) {
    this.orderId = orderId;
    this.payType = payType;
    this.payTime = payTime;
    this.amount = amount;
  }

  public long getOrderId() {
    return orderId;
  }

  public String getPayType() {
    return payType;
  }

  public Date getPayTime() {
    return payTime;
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
    return new HashMap<String, Object>() {{
      put("uri", "orders/" + orderId + "payment");
      put("order_uri", "orders/" + orderId);
      put("pay_type", payType);
      put("amount", amount);
      put("created_at", payTime);
    }};
  }
}
