package com.thoughtworks.ketsu.domain;

import javax.ws.rs.BadRequestException;
import java.util.Map;
import java.util.Optional;

public class Validators {
    public static void validate(Validator validator, Map<String, Object> info) {
        Optional<String> message = validator.validate(info);

        if (message.isPresent()) {
            throw new BadRequestException(message.get());
        }
    }

    public static Validator fieldEmptyValidator(String name, String message) {
        return (info) -> info.getOrDefault(name, "").toString().trim().isEmpty() ? Optional.of(message) : Optional.empty();
    }
}
