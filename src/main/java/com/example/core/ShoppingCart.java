package com.example.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShoppingCart {
    @JsonProperty
    private Integer id;

    @NotNull
    @JsonProperty
    private String email;

    @JsonProperty
    private Set<CartItem> items = new HashSet<>();

    public ShoppingCart() {
        // Jackson deserialization
    }

    public ShoppingCart(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingCart)) return false;

        ShoppingCart that = (ShoppingCart) o;

        if (!getId().equals(that.getId())) return false;
        if (!getEmail().equals(that.getEmail())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public void addItem(CartItem cartItem) {
        items.add(cartItem);
    }

    public void addItems(Collection<CartItem> cartItems) {
        items.addAll(cartItems);
    }

    public Set<CartItem> getItems() {
        return items;
    }
}
