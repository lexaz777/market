package ru.zakharov.market.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zakharov.market.entities.Product;
import ru.zakharov.market.utils.Cart;

import javax.servlet.http.HttpSession;

@Service
public class ShoppingCartService {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public Cart getCurrentCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    public void addToCart(HttpSession session, Long productId) {
        Product product = productService.getProductById(productId);
        addToCart(session, product);
    }

    public void addToCart(HttpSession session, Product product) {
        Cart cart = getCurrentCart(session);
        cart.addToCart(product);
    }

    public void removeFromCart(HttpSession session, Long productId) {
        Product product = productService.getProductById(productId);
        removeFromCart(session, product);
    }

    public void removeFromCart(HttpSession session, Product product) {
        Cart cart = getCurrentCart(session);
        cart.removeFromCart(product);
    }
}
