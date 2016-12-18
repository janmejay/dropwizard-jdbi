package com.example;

import com.example.dao.CartItemDAO;
import com.example.service.CartService;
import com.example.dao.ShoppingCartDAO;
import com.example.resources.ShoppingCartResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.jdbi.DBIFactory;
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

        final ShoppingCartDAO shoppingCartDAO = jdbi.onDemand(ShoppingCartDAO.class);
        CartItemDAO cartItemDAO = jdbi.onDemand(CartItemDAO.class);
        CartService cartService = new CartService(shoppingCartDAO, cartItemDAO);
        final ShoppingCartResource shoppingCartResource = new ShoppingCartResource(cartService);

        environment.jersey().register(shoppingCartResource);
    }
}
