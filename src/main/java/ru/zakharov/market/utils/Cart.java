package ru.zakharov.market.utils;

import ru.zakharov.market.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> cartItems;


    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public void addToCart(Product product) {
        cartItems.add(new CartItem(product, 1));
    }

    public void removeFromCart(Product product) {
        cartItems.remove(new CartItem(product, 1));
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
