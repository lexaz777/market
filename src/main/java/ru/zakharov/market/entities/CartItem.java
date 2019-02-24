package ru.zakharov.market.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "subtotal")
    private int subtotal;

    @ManyToOne
    @JoinColumn(name = "shop_order_id")
    @JsonBackReference
    private ShopOrder shopOrder;

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

    public CartItem() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addOneProduct() {
        quantity++;
        refreshSubtotal();
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) return;
        this.quantity = quantity;
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

    public ShopOrder getShopOrder() {
        return shopOrder;
    }

    public void setShopOrder(ShopOrder shopOrder) {
        this.shopOrder = shopOrder;
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
