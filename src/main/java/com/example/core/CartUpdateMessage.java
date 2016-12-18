package com.example.core;

public class CartUpdateMessage {
    private final String text;
    private final String email;
    public CartUpdateMessage(String text, String email) {
        this.text = text;
        this.email = email;
    }

    @Override
    public String toString() {
        return "CartUpdateMessage{" +
                "text='" + text + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartUpdateMessage that = (CartUpdateMessage) o;

        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        return email != null ? email.equals(that.email) : that.email == null;

    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
