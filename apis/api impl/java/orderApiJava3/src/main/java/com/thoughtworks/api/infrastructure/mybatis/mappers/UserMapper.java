package com.thoughtworks.api.infrastructure.mybatis.mappers;

import com.thoughtworks.api.domain.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
  void save(@Param("info") Map<String, Object> info);

  User findById(@Param("id") long id);

  List<User> find();
}
