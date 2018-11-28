package ru.zakharov.market.utils;

import ru.zakharov.market.entities.Product;

public class CartItem {
    private Product product;
    private int quantity;
    private int subtotal;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        refreshSubtotal();
    }

    public CartItem(Product product) {
        this.product = product;
        this.quantity = 1;
        refreshSubtotal();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addOneProduct() {
        quantity++;
        refreshSubtotal();
    }

    public void removeOneProduct() {
        if (quantity > 0) quantity--;
        refreshSubtotal();
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void refreshSubtotal() {
        subtotal = product.getPrice() * quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof CartItem)) return false;
        CartItem otherCartItem = (CartItem) obj;
        if (otherCartItem.getProduct().getId() != this.getProduct().getId()) return false;
        return true;
    }
}
