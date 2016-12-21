package com.thoughtworks.ketsu.api.jersey;

import com.thoughtworks.ketsu.domain.Customer;
import com.thoughtworks.ketsu.domain.Price;
import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.domain.user.User;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class Routes {

    private final String baseUri;

    public Routes(UriInfo uriInfo) {
        baseUri = uriInfo.getBaseUri().toASCIIString();
    }

    public URI userUrl(User user) {
        return URI.create(String.format("%susers/%s", baseUri, user.getUserId().id()));
    }

    public URI customerUrl(Customer customer) {
        return URI.create(String.format("%scustomers/%s", baseUri, customer.getId()));
    }

    public URI productUrl(Product product) {
        return URI.create(String.format("%sproducts/%s", baseUri, product.getId()));
    }

    public URI priceUrl(Product product, Price price) {
        return URI.create(String.format("%sproducts/%s/prices/%s", baseUri, product.getId(), price.getId()));
    }
}
