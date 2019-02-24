package ru.zakharov.market.dto;

import lombok.Data;
import ru.zakharov.market.entities.CartItem;

import java.util.List;

@Data
public class ShopOrderTransaction {
    private long orderId;
    private List<CartItem> carItemList;
    private double totalPrice;
}
