package com.example.service;

import com.example.core.CartItem;
import com.example.core.ShoppingCart;
import com.example.dao.CartItemDAO;
import com.example.dao.ShoppingCartDAO;

import java.util.List;

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
    }

    public void save(ShoppingCart cart) {
        if (cart.getId() == null) {
            shoppingCartDAO.insert(cart);
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
}
