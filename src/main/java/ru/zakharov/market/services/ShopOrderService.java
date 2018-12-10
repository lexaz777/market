package ru.zakharov.market.services;

import org.hibernate.query.criteria.internal.OrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zakharov.market.entities.CartItem;
import ru.zakharov.market.entities.ShopOrder;
import ru.zakharov.market.entities.User;
import ru.zakharov.market.repositories.CartItemRepository;
import ru.zakharov.market.repositories.ShopOrderRepository;
import ru.zakharov.market.utils.Cart;

@Service
public class ShopOrderService {
    private ShopOrderRepository shopOrderRepository;
    private CartItemRepository cartItemRepository;

    @Autowired
    public void setCartItemRepository(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Autowired
    public void setShopOrderRepository(ShopOrderRepository shopOrderRepository) {
        this.shopOrderRepository = shopOrderRepository;
    }

    @Transactional
    public ShopOrder makeOrder(Cart cart, User user) {
        ShopOrder shopOrder = new ShopOrder();
        System.out.println(shopOrder);
        shopOrder.setUser(user);
        shopOrder = shopOrderRepository.save(shopOrder);
        shopOrder.setCartItemList(cart.getCartItems());
        System.out.println(shopOrder);
        for (CartItem cartItem : cart.getCartItems()) {
            cartItem.setShopOrder(shopOrder);
        }
        System.out.println(shopOrder);
        shopOrder = shopOrderRepository.save(shopOrder);
        return shopOrder;
    }

    public ShopOrder save(ShopOrder shopOrder) {
        shopOrder = shopOrderRepository.save(shopOrder);
        return shopOrder;
    }

    public ShopOrder getShopOrderById(int id) {
        return shopOrderRepository.findById(id);
    }
}
