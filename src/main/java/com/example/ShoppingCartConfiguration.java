package com.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.client.HttpClientConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ShoppingCartConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    @Valid
    @NotNull
    @JsonProperty
    private HttpClientConfiguration httpClient = new HttpClientConfiguration();

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    public HttpClientConfiguration getHttpClientConfiguration() {
        return httpClient;
    }
}
