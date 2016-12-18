package toy.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class CartItem {
    @JsonProperty
    private Integer id;

    private Integer cartId;

    @NotNull
    @JsonProperty
    private String productName;

    @NotNull
    @JsonProperty
    private Integer quantity;

    public CartItem() {
        // Jackson deserialization
    }

    public CartItem(int id, int cartId, String productName, int quantity) {
        this.id = id;
        this.cartId = cartId;
        this.productName = productName;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getCartId() {
        return cartId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem)) return false;

        CartItem that = (CartItem) o;

        if (!getId().equals(that.getId())) return false;
        if (!getCartId().equals(that.getCartId())) return false;
        if (!getProductName().equals(that.getProductName())) return false;
        if (!getQuantity().equals(that.getQuantity())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cartId != null ? cartId.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", cartId=" + cartId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }
}
