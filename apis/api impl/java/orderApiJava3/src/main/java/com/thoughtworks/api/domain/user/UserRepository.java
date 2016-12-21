package com.thoughtworks.api.domain.user;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserRepository {
  void create(Map<String, Object> info);

  Optional<User> findById(long id);

  List<User> find();
}
