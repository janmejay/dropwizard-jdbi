package com.example.dao;

import com.example.core.ShoppingCart;
import com.example.core.mapper.ShoppingCartMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(ShoppingCartMapper.class)
public interface ShoppingCartDAO {

    @SqlQuery("select * from CARTS")
    List<ShoppingCart> getAll();

    @SqlQuery("select * from CARTS where ID = :id")
    ShoppingCart findById(@Bind("id") int id);

    @SqlQuery("select * from CARTS where EMAIL = :email")
    ShoppingCart findByEmail(@Bind("email") String email);

    @SqlUpdate("delete from CARTS where ID = :id")
    int deleteById(@Bind("id") int id);

    @SqlUpdate("update CARTS set NAME = :name where ID = :id")
    int update(@BindBean ShoppingCart shoppingCart);

    @SqlUpdate("insert into CARTS (ID, NAME) values (:id, :name)")
    int insert(@BindBean ShoppingCart shoppingCart);
}
