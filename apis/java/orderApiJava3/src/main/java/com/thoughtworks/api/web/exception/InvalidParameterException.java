package com.thoughtworks.api.web.exception;

import java.util.ArrayList;
import java.util.List;

public class InvalidParameterException extends RuntimeException {
    List<InvalidParameterMessage> jsonMessage;

    public InvalidParameterException(String message) {
        super(message);
    }

    public InvalidParameterException() {
        super();
    }

    public InvalidParameterException(Exception e) {
        super(e);
    }

    public List<InvalidParameterMessage> getJsonMessage() {
        return jsonMessage;
    }

    public InvalidParameterException(List<String> args) {
        jsonMessage = new ArrayList<>();
        for (String arg: args) {
            jsonMessage.add(new InvalidParameterMessage(arg));
        }
    }
}
