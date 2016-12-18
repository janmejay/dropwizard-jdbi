package com.example.dao;

import com.example.core.CartItem;
import com.example.core.mapper.CartItemMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * @understands
 */
@RegisterMapper(CartItemMapper.class)
public interface CartItemDAO {
    @SqlQuery("select * from CART_ITEMS where CART_ID = :cart_id")
    List<CartItem> loadItems(@Bind("cart_id") int cartId);

    @SqlUpdate("insert into CART_ITEMS (CART_ID, PRODUCT_NAME, QUANTITY) values (:cartId, :productName, :quantity)")
    void insert(@BindBean CartItem item);
}
