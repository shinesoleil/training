package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.domain.order.Order;
import com.thoughtworks.api.domain.payment.Payment;
import com.thoughtworks.api.domain.user.User;
import com.thoughtworks.api.support.DatabaseTestRunner;
import com.thoughtworks.api.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(DatabaseTestRunner.class)
public class PaymentManipulationTest {
  @Inject
  com.thoughtworks.api.domain.product.ProductRepository productRepository;

  @Inject
  com.thoughtworks.api.domain.user.UserRepository userRepository;

  @Test
  public void should_create_payment_and_find_payment_by_id() {
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
    long paymentId = Long.valueOf(String.valueOf(paymentInfo.get("id")));

    Optional<Payment> paymentOptional = order.findPaymentById(paymentId);

    assertThat(paymentOptional.get().getOrderId(), is(paymentId));

  }
}
