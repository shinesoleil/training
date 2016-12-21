package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.domain.user.User;
import com.thoughtworks.api.domain.user.UserRepository;
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
public class UserRepositoryTest {

  @Inject
  UserRepository userRepository;

  @Test
  public void should_create_product_and_find_product_by_id() {
    Map<String, Object> info = TestHelper.userMap();

    userRepository.create(info);
    long id = Long.valueOf(String.valueOf(info.get("id")));
    Optional<User> userOptional = userRepository.findById(id);

    assertThat(userOptional.get().getId(), is(id));
  }

  @Test
  public void should_find_all_users() {
    Map<String, Object> info = TestHelper.userMap();
    userRepository.create(info);
    long id = Long.valueOf(String.valueOf(info.get("id")));

    List<User> userList = userRepository.find();

    assertThat(userList.size(), is(1));
    assertThat(userList.get(0).getId(), is(id));
  }
}
