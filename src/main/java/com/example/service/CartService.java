package com.example.service;

import com.example.core.CartItem;
import com.example.core.ShoppingCart;
import com.example.dao.CartItemDAO;
import com.example.dao.ShoppingCartDAO;

import java.util.List;
import java.util.Set;

public class CartService {
    private final ShoppingCartDAO shoppingCartDAO;
    private final CartItemDAO cartItemDAO;

    public CartService(ShoppingCartDAO shoppingCartDAO, CartItemDAO cartItemDAO) {
        this.shoppingCartDAO = shoppingCartDAO;
        this.cartItemDAO = cartItemDAO;
    }

    public ShoppingCart getCartByEmail(String email) {
        ShoppingCart cart = shoppingCartDAO.findByEmail(email);
        loadItems(cart);
        return cart;
    }

    private void loadItems(ShoppingCart cart) {
        List<CartItem> cartItems = cartItemDAO.loadItems(cart.getId());
        cart.addItems(cartItems);
        if (cart.getItemCount() != cartItems.size())
            throw new IllegalStateException(String.format("De-normalized cart-item count {} doesn't match actual {}.", cart.getItemCount(), cartItems.size()));
        int quantity = getQuantity(cart.getItems());
        if (cart.getItemQuantity() != quantity) {
            throw new IllegalStateException(String.format("De-normalized cart-item quantity {} doesn't match actual {}.", cart.getItemQuantity(), quantity));
        }
    }

    public void save(ShoppingCart cart) {
        Set<CartItem> items = cart.getItems();
        int quantity = getQuantity(cart.getItems());
        cart.setItemQuantity(quantity);
        cart.setItemCount(items.size());

        if (cart.getId() == null) {
            shoppingCartDAO.insert(cart);
        } else {
            shoppingCartDAO.update(cart);
        }
        if (cart.getId() == null) {
            cart = shoppingCartDAO.findByEmail(cart.getEmail());
        }
        for (CartItem item : cart.getItems()) {
            if (item.getId() == null) {
                item.setCartId(cart.getId());
                cartItemDAO.insert(item);
            }
        }
    }

    private int getQuantity(final Set<CartItem> items) {
        int quantity = 0;
        for (CartItem item : items) {
            quantity += item.getQuantity();
        }
        return quantity;
    }
}
