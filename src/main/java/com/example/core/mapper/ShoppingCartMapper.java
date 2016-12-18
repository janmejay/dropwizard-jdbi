package com.example.core.mapper;

import com.example.core.CartItem;
import com.example.core.ShoppingCart;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShoppingCartMapper implements ResultSetMapper<ShoppingCart> {
    public ShoppingCart map(int index, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new ShoppingCart(resultSet.getInt("ID"), resultSet.getString("EMAIL"), resultSet.getInt("ITEM_COUNT"), resultSet.getInt("ITEM_QUANTITY"));
    }
}
