package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.domain.user.User;
import com.thoughtworks.api.infrastructure.mybatis.mappers.UserMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserRepository implements com.thoughtworks.api.domain.user.UserRepository {
  @Inject
  UserMapper userMapper;

  @Override
  public void create(Map<String, Object> info) {
    userMapper.save(info);
  }

  @Override
  public Optional<User> findById(long id) {
    return Optional.ofNullable(userMapper.findById(id));
  }

  @Override
  public List<User> find() {
    return userMapper.find();
  }
}
