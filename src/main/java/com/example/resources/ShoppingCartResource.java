package com.example.resources;

import com.example.core.CartItem;
import com.example.core.ShoppingCart;
import com.example.service.CartService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/cart")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class ShoppingCartResource {
    CartService cartService;

    public ShoppingCartResource(CartService cartService) {
        this.cartService = cartService;
    }

    @GET
    @Path("/{email}")
    public ShoppingCart get(@PathParam("email") String email){
        return cartService.getCartByEmail(email);
    }

    @POST
    @Path("/{email}/add")
    public void add(@PathParam("email") String email, @Valid CartItem cartItem) {
        ShoppingCart cart = cartService.getCartByEmail(email);
        cart.addItem(cartItem);
        cartService.save(cart);
    }
}
