package com.thoughtworks.api.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestHelper {
    private static int auto_increment_key = 1;
    public static Map<String, Object> deployment(String appName, String releaseId) {
        return new HashMap<String, Object>() {{
            put("app", String.format("http://service-api.tw.com/apps/%s", appName));
            put("release", String.format("http://service-api.tw.com/apps/%s/releases/%s", appName, releaseId));
        }};
    }

    public static Map<String, Object> stackMap(String id, String name) {
        Map<String, Object> stackMap = new HashMap<String, Object>() {{
            put("id", id);
            put("name", name);
        }};
        return stackMap;
    }



//    public static User userForTest(String id, String name, UserRole role) {
//        String password_123 = "$2a$04$DbgJbGA4dkQSzAvXvJcGBOv5kHuMBzrWfne3x3Cx4JQv4IJcxtBIW";
//        return new User(new UserId(id), name, name + "@tw.com", role, password_123);
//    }
//
//    public static User userFixture(UserRepository userRepository, UserRole role) {
//        final String id = new Integer(auto_increment_key++).toString();
//        User user = userForTest(id, "name-" + id, role);
//        userRepository.save(user);
//        return user;
//    }
//
//    public static Map<String, Object> userJsonForTest(User user) {
//        return new HashMap<String, Object>() {{
//            put("id", user.getUserId().id());
//            put("role", user.getRole());
//        }};
//    }

    public static Map<String, Object> productMap() {
        return new HashMap<String, Object>() {{
            put("name", "desk");
            put("description", "black");
            put("price", 560);
        }};
    }

    public static Map<String, Object> userMap() {
        return new HashMap<String, Object>() {{
            put("name", "firstUser");
        }};
    }

    public static Map<String, Object> orderMap(long productId, long userId) {
        return new HashMap<String, Object>() {{
            put("user_id", userId);
            put("name", "firstOrder");
            put("address", "Beijing");
            put("phone", "13099999999");
            put("order_items", orderItemsMap((productId)));
        }};
    }

    public static List<Map<String, Object>> orderItemsMap(long productId) {
        Map<String, Object> item =  new HashMap<String, Object>() {{
            put("quantity", 2);
            put("product_id", productId);
        }};

        return new ArrayList<Map<String, Object>>() {{
            add(item);
        }};
    }

    public static Map<String, Object> paymentMap(long orderId) {
        return new HashMap<String, Object>() {{
            put("order_id", orderId);
            put("pay_type", "CASH");
            put("amount", 1500);
        }};
    }

}

