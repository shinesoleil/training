package com.thoughtworks.api.web.jersey;

import com.thoughtworks.api.domain.product.Product;
import com.thoughtworks.api.domain.user.User;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class Routes {

    private final String baseUri;

    public Routes(UriInfo uriInfo) {
        baseUri = uriInfo.getBaseUri().toASCIIString();
    }

    public URI userUrl(User user) {
        return URI.create("users/" + user.getId());
    }

    public URI productUrl(Product product) {
        return URI.create("products/" + product.getId());
    }
}
