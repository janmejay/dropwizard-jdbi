package toy.core.mapper;

import toy.core.CartItem;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartItemMapper implements ResultSetMapper<CartItem> {
    public CartItem map(int index, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new CartItem(resultSet.getInt("ID"), resultSet.getInt("CART_ID"), resultSet.getString("PRODUCT_NAME"), resultSet.getInt("QUANTITY"));
    }
}
