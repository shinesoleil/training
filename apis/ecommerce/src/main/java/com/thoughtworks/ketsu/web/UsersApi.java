package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.User;
import com.thoughtworks.ketsu.domain.UserRepository;
import com.thoughtworks.ketsu.domain.Validators;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Path("users")
public class UsersApi {

    @Context
    UserRepository userRepository;

    @Context
    Routes routes;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(Map<String, Object> userInfo) {
        Validators.validate(Validators.fieldEmptyValidator("email", "email should not be empty"), userInfo);
        Validators.validate(Validators.fieldEmptyValidator("password", "password should not be empty"), userInfo);

        User user = userRepository.create(userInfo);

        return Response.status(201).location(routes.userUrl(user)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Path("{userId}")
    public UserApi getUserApi(@PathParam("userId") long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (!userOptional.isPresent()) {
            throw new NotFoundException("user not found");
        }

        return new UserApi(userOptional.get());
    }

}
