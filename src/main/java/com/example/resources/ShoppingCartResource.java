package com.example.resources;

import com.example.core.CartItem;
import com.example.core.CartUpdateMessage;
import com.example.core.ShoppingCart;
import com.example.dao.CartService;
import com.example.remote.NotificationService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/cart")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class ShoppingCartResource {
    final CartService cartService;
    final NotificationService notificationService;

    public ShoppingCartResource(CartService cartService, NotificationService notificationService) {
        this.cartService = cartService;
        this.notificationService = notificationService;
    }

    @GET
    @Path("/{email}")
    public ShoppingCart get(@PathParam("email") String email){
        return cartService.getCartByEmail(email);
    }

    @POST
    @Path("/{email}/add")
    public String add(@PathParam("email") String email, @Valid CartItem cartItem) {
        ShoppingCart cart = cartService.getCartByEmail(email);
        cart.addItem(cartItem);
        cartService.save(cart, notificationService::sendCartUpdateMessage);
        return "Added successfully";
    }
}
