package com.thoughtworks.ketsu.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserRepository {
    User create(Map<String, Object> info);

    List<User> findAll();

    Optional<User> findById(long i);
}
