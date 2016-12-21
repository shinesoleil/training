package com.thoughtworks.api.web;

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
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class OrderApiTest extends ApiSupport{

  @Inject
  ProductRepository productRepository;

  @Inject
  UserRepository userRepository;

  @Test
  public void should_return_201_when_post_order() {
    Map<String, Object> productInfo = TestHelper.productMap();
    productRepository.create(productInfo);
    long productId = Long.valueOf(String.valueOf(productInfo.get("id")));

    Map<String, Object> userInfo = TestHelper.userMap();
    userRepository.create(userInfo);
    long userId = Long.valueOf(String.valueOf(userInfo.get("id")));

    Map<String, Object> orderInfo = TestHelper.orderMap(productId, userId);

    Response post = post("users/" + userId+ "/orders", orderInfo);

    assertThat(post.getStatus(), is(201));
  }

  @Test
  public void should_return_list_of_order_when_get_orders() {
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

    Response get = get("users/" + userId + "/orders");
    List<Map<String, Object>> mapList = get.readEntity(List.class);

    assertThat(get.getStatus(), is(200));
    assertThat(mapList.size(), is(1));
    assertThat(mapList.get(0).get("name"), is("firstOrder"));
  }

  @Test
  public void should_return_order_json_when_get_order_by_id() {
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

    Response get = get("users/" + userId + "/orders/" + orderId);
    Map<String, Object> map = get.readEntity(Map.class);

    assertThat(map.get("name"), is("firstOrder"));
  }

  @Test
  public void should_return_404_when_get_order_not_found() {
    Map<String, Object> userInfo = TestHelper.userMap();
    userRepository.create(userInfo);
    long userId = Long.valueOf(String.valueOf(userInfo.get("id")));

    Response get = get("users/" + userId + "/orders/1");

    assertThat(get.getStatus(), is(404));
  }
}
