package ru.zakharov.market.utils;

import ru.zakharov.market.entities.CartItem;
import ru.zakharov.market.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> cartItems;
    private int totalPrice;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public void addToCart(Product product) {
        int quantity = 0;
        CartItem cartItem = new CartItem(product);
        int index = cartItems.indexOf(cartItem);
        if (index != -1)
            quantity = cartItems.get(index).getQuantity();
        if (quantity >= 1)
            cartItems.get(index).addOneProduct();
        else
            cartItems.add(cartItem);
        refreshTotalPrice();
    }

/*    public void removeFromCart(Product product) {
        int quantity = 0;
        CartItem cartItem = new CartItem(product);
        int index = cartItems.indexOf(cartItem);
        if (index != -1)
            quantity = cartItems.get(index).getQuantity();
        if (quantity > 1)
            cartItems.get(index).removeOneProduct();
        else
            cartItems.remove(cartItem);
        refreshTotalPrice();
    }*/

    public void removeFromCart(Product product) {
        int quantity = 0;
        CartItem cartItem = new CartItem(product);
        int index = cartItems.indexOf(cartItem);
        if (index != -1) cartItems.remove(cartItem);
        refreshTotalPrice();
    }

    public void setQuantity(Product product, int quantity) {
        CartItem cartItem = new CartItem(product);
        int index = cartItems.indexOf(cartItem);
        cartItems.get(index).setQuantity(quantity);
        refreshTotalPrice();
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    private void refreshTotalPrice() {
        totalPrice = 0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getSubtotal();
        }
    }


}
