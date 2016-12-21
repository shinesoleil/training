package com.thoughtworks.api.web;

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
public class UsersApiTest extends ApiSupport {

  @Inject
  UserRepository userRepository;

  @Test
  public void should_return_201_when_post_user_with_parameters() {
    Map<String, Object> info = TestHelper.userMap();

    Response post = post("users", info);

    assertThat(post.getStatus(), is(201));
  }

  @Test
  public void should_return_400_when_post_with_invalid_parameters() {
    Map<String, Object> info = TestHelper.userMap();
    info.replace("name", null);

    Response post = post("users", info);

    assertThat(post.getStatus(), is(400));
  }

  @Test
  public void should_return_user_list_json_when_get_users() {
    Map<String, Object> info = TestHelper.userMap();
    userRepository.create(info);
    long id = Long.valueOf(String.valueOf(info.get("id")));

    Response get = get("users");
    List<Map<String, Object>> userList = get.readEntity(List.class);

    assertThat(get.getStatus(), is(200));
    assertThat(Long.valueOf(String.valueOf(userList.get(0).get("id"))), is(id));
  }

  @Test
  public void should_return_user_json_when_get_user_by_id() {
    Map<String, Object> info = TestHelper.userMap();
    userRepository.create(info);
    long id = Long.valueOf(String.valueOf(info.get("id")));

    Response get = get("users/" + id);
    Map<String, Object> map = get.readEntity(Map.class);

    assertThat(map.get("name"), is("firstUser"));
  }

  @Test
  public void should_return_404_when_get_user_not_found() {
    Response get = get("users/1");

    assertThat(get.getStatus(), is(404));
  }
}
