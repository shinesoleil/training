package com.thoughtworks.ketsu.domain;

import java.util.Map;
import java.util.Optional;

public interface Validator {
    Optional<String> validate(Map<String, Object> info);
}
