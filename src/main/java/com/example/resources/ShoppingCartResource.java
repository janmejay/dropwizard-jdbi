package com.example.resources;

import com.example.core.ShoppingCart;
import com.example.dao.ShoppingCartDAO;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/cart")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class ShoppingCartResource {

    ShoppingCartDAO shoppingCartDAO;

    public ShoppingCartResource(ShoppingCartDAO shoppingCartDAO) {
        this.shoppingCartDAO = shoppingCartDAO;
    }

    @GET
    public List<ShoppingCart> getAll(){
        return shoppingCartDAO.getAll();
    }

    @GET
    @Path("/{id}")
    public ShoppingCart get(@PathParam("id") Integer id){
        return shoppingCartDAO.findById(id);
    }

    @POST
    public ShoppingCart add(@Valid ShoppingCart shoppingCart) {
        shoppingCartDAO.insert(shoppingCart);

        return shoppingCart;
    }

    @PUT
    @Path("/{id}")
    public ShoppingCart update(@PathParam("id") Integer id, @Valid ShoppingCart shoppingCart) {
        ShoppingCart updateShoppingCart = new ShoppingCart(id, shoppingCart.getName());

        shoppingCartDAO.update(updateShoppingCart);

        return updateShoppingCart;
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        shoppingCartDAO.deleteById(id);
    }
}
