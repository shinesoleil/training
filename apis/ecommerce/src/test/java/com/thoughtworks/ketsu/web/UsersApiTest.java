package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.User;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(ApiTestRunner.class)
public class UsersApiTest extends ApiSupport {

    @Test
    public void should_201_when_creating_user() {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("email", "test@email.com");
        userInfo.put("password", "123");

        User newUser = new User(1, "user@email.com");

        when(userRepository.create(any())).thenReturn(newUser);

        Response response = post("/users", userInfo);

        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toASCIIString().contains("users/1"), is(true));
    }

    @Test
    public void should_400_when_creating_user_with_invalid_info() {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("email", "");
        userInfo.put("password", "");

        Response response = post("/users", userInfo);

        assertThat(response.getStatus(), is(400));
    }

    @Test
    public void should_200_when_reading_users() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "test@email.com"));

        when(userRepository.findAll()).thenReturn(users);

        Response response = get("/users");

        assertThat(response.getStatus(), is(200));
        assertThat(response.readEntity(List.class).size(), is(1));
    }

    @Test
    public void should_200_when_finding_user_by_id() {
        User user = new User(1, "test@email.com");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Response response = get("/users/1");

        assertThat(response.getStatus(), is(200));
        assertThat(response.readEntity(Map.class).get("email").toString(), is("test@email.com"));
    }

    @Test
    public void should_404_when_no_user_found_by_id() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        Response response = get("/users/1");

        assertThat(response.getStatus(), is(404));
    }
}