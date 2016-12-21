package com.thoughtworks.api.web;

import com.thoughtworks.api.domain.order.Order;
import com.thoughtworks.api.domain.product.ProductRepository;
import com.thoughtworks.api.domain.user.User;
import com.thoughtworks.api.domain.user.UserRepository;
import com.thoughtworks.api.support.ApiSupport;
import com.thoughtworks.api.support.ApiTestRunner;
import com.thoughtworks.api.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class PaymentApiTest extends ApiSupport{
  @Inject
  ProductRepository productRepository;

  @Inject
  UserRepository userRepository;

  @Test
  public void should_return_201_when_post_payment_with_parameters() {
    Map<String, Object> productInfo = TestHelper.productMap();
    productRepository.create(productInfo);
    long productId = Long.valueOf(String.valueOf(productInfo.get("id")));

    Map<String, Object> userInfo = TestHelper.userMap();
    userRepository.create(userInfo);
    long userId = Long.valueOf(String.valueOf(userInfo.get("id")));

    Map<String, Object> orderInfo = TestHelper.orderMap(productId, userId);

    User user = userRepository.findById(userId).get();
    user.placeOrder(orderInfo);
    long orderId = Long.valueOf(String.valueOf(orderInfo.get("id")));
    Order order = user.findOrderById(orderId).get();



    Response post = post("users/" + userId + "/orders/" + orderId + "/payment", TestHelper.paymentMap(orderId));

    assertThat(post.getStatus(), is(201));
  }

  @Test
  public void should_return_payment_when_get_payment_by_order_id() {
    Map<String, Object> productInfo = TestHelper.productMap();
    productRepository.create(productInfo);
    long productId = Long.valueOf(String.valueOf(productInfo.get("id")));

    Map<String, Object> userInfo = TestHelper.userMap();
    userRepository.create(userInfo);
    long userId = Long.valueOf(String.valueOf(userInfo.get("id")));

    Map<String, Object> orderInfo = TestHelper.orderMap(productId, userId);

    User user = userRepository.findById(userId).get();
    user.placeOrder(orderInfo);
    long orderId = Long.valueOf(String.valueOf(orderInfo.get("id")));
    Order order = user.findOrderById(orderId).get();

    Map<String, Object> paymentInfo = TestHelper.paymentMap(orderId);
    order.pay(paymentInfo);

    Response get = get("users/" + userId + "/orders/" + orderId + "/payment");
    Map<String, Object> map = get.readEntity(Map.class);

    assertThat(map.get("pay_type"), is("CASH"));

  }

  @Test
  public void should_return_404_when_get_payment_not_found() {
    Map<String, Object> productInfo = TestHelper.productMap();
    productRepository.create(productInfo);
    long productId = Long.valueOf(String.valueOf(productInfo.get("id")));

    Map<String, Object> userInfo = TestHelper.userMap();
    userRepository.create(userInfo);
    long userId = Long.valueOf(String.valueOf(userInfo.get("id")));

    Map<String, Object> orderInfo = TestHelper.orderMap(productId, userId);

    User user = userRepository.findById(userId).get();
    user.placeOrder(orderInfo);
    long orderId = Long.valueOf(String.valueOf(orderInfo.get("id")));

    Response get = get("users/" + userId + "/orders/" + orderId + "/payment");

    assertThat(get.getStatus(), is(404));
  }
}
