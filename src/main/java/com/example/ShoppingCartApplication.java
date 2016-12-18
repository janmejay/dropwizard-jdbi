package com.example;

import com.example.dao.ShoppingCartDAO;
import com.example.resources.ShoppingCartResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.jdbi.DBIFactory;
import org.skife.jdbi.v2.DBI;

public class ShoppingCartApplication extends Application<ExampleConfiguration> {
    public static void main(String[] args) throws Exception {
        new ShoppingCartApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard-jdbi";
    }

    @Override
    public void initialize(Bootstrap<ExampleConfiguration> bootstrap) {
    }

    @Override
    public void run(ExampleConfiguration configuration, Environment environment) throws ClassNotFoundException {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "h2");

        final ShoppingCartDAO shoppingCartDAO = jdbi.onDemand(ShoppingCartDAO.class);
        final ShoppingCartResource shoppingCartResource = new ShoppingCartResource(shoppingCartDAO);

        environment.jersey().register(shoppingCartResource);
    }
}
