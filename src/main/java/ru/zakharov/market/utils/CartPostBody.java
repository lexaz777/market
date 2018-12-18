package ru.zakharov.market.utils;

import org.springframework.stereotype.Component;

@Component
public class CartPostBody {
    private Long id;
    private int quantity;

    public CartPostBody() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
