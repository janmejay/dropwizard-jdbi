package com.example.dao;

import com.example.core.ShoppingCart;
import com.example.core.mapper.ShoppingCartMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(ShoppingCartMapper.class)
public interface ShoppingCartDAO {
    @SqlQuery("select * from CARTS where ID = :id")
    ShoppingCart findById(@Bind("id") int id);

    @SqlQuery("select * from CARTS where EMAIL = :email")
    ShoppingCart findByEmail(@Bind("email") String email);

    @SqlUpdate("insert into CARTS (NAME) values (:email)")
    int insert(@BindBean ShoppingCart shoppingCart);
}
