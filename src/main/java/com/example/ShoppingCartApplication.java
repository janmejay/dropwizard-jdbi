package com.example;

import com.example.dao.CartItemDAO;
import com.example.dao.CartService;
import com.example.remote.NotificationService;
import com.example.resources.ShoppingCartResource;
import io.dropwizard.Application;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.http.impl.client.CloseableHttpClient;
import org.skife.jdbi.v2.DBI;

public class ShoppingCartApplication extends Application<ShoppingCartConfiguration> {
    public static void main(String[] args) throws Exception {
        new ShoppingCartApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard-jdbi";
    }

    @Override
    public void initialize(Bootstrap<ShoppingCartConfiguration> bootstrap) {
    }

    @Override
    public void run(ShoppingCartConfiguration configuration, Environment environment) throws ClassNotFoundException {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "h2");

        final CartService cartService = jdbi.onDemand(CartService.class);
        CartItemDAO cartItemDAO = jdbi.onDemand(CartItemDAO.class);
        cartService.setCartItemDAO(cartItemDAO);
        final CloseableHttpClient httpClient =  new HttpClientBuilder(environment).using(configuration.getHttpClientConfiguration()).build("notification_client");
        final ShoppingCartResource shoppingCartResource = new ShoppingCartResource(cartService, new NotificationService(httpClient));

        environment.jersey().register(shoppingCartResource);
    }
}
