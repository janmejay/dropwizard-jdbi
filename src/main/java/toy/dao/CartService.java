package toy.dao;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import toy.core.CartItem;
import toy.core.CartUpdateMessage;
import toy.core.ShoppingCart;
import toy.core.mapper.ShoppingCartMapper;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

@RegisterMapper(ShoppingCartMapper.class)
public abstract class CartService {
    private CartItemDAO cartItemDAO;

    @SqlQuery("select * from CARTS where ID = :id")
    abstract ShoppingCart findById(@Bind("id") int id);

    @SqlQuery("select * from CARTS where EMAIL = :email")
    abstract ShoppingCart findByEmail(@Bind("email") String email);

    @SqlUpdate("insert into CARTS (NAME) values (:email)")
    abstract int insert(@BindBean ShoppingCart shoppingCart);

    @SqlUpdate("update CARTS set ITEM_COUNT = :itemCount, ITEM_QUANTITY = :itemQuantity where ID = :id")
    abstract void update(@BindBean ShoppingCart shoppingCart);

    public void setCartItemDAO(CartItemDAO cartItemDAO) {
        this.cartItemDAO = cartItemDAO;
    }

    public ShoppingCart getCartByEmail(String email) {
        ShoppingCart cart = findByEmail(email);
        loadItems(cart);
        return cart;
    }

    private void loadItems(ShoppingCart cart) {
        if (cart != null) {
            List<CartItem> cartItems = cartItemDAO.loadItems(cart.getId());
            cart.addItems(cartItems);
            if (cart.getItemCount() != cartItems.size())
                throw new IllegalStateException(String.format("De-normalized cart-item count %s doesn't match actual %s.", cart.getItemCount(), cartItems.size()));
            int quantity = getQuantity(cart.getItems());
            if (cart.getItemQuantity() != quantity) {
                throw new IllegalStateException(String.format("De-normalized cart-item quantity %s doesn't match actual %s.", cart.getItemQuantity(), quantity));
            }
        }
    }

    @Transaction
    public void save(ShoppingCart cart, Function<CartUpdateMessage, Void> notifier) {
        Set<CartItem> items = cart.getItems();
        int quantity = getQuantity(cart.getItems());
        cart.setItemQuantity(quantity);
        cart.setItemCount(items.size());

        if (cart.getId() == null) {
            insert(cart);
        } else {
            update(cart);
        }

        generateNotifications(cart, notifier);

        if (cart.getId() == null) {
            cart = findByEmail(cart.getEmail());
        }
        for (CartItem item : cart.getItems()) {
            if (item.getId() == null) {
                item.setCartId(cart.getId());
                cartItemDAO.insert(item);
            }
        }
    }

    private void generateNotifications(ShoppingCart cart, Function<CartUpdateMessage, Void> notifier) {
        for (CartItem item : cart.getItems()) {
            if (item.getId() == null) {
                String text = String.format("Adding %s units of %s to your cart.", item.getQuantity(), item.getProductName());
                notifier.apply(new CartUpdateMessage(text, cart.getEmail()));
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
