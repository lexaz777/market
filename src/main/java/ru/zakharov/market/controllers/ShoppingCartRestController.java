package ru.zakharov.market.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.zakharov.market.services.ShoppingCartService;
import ru.zakharov.market.utils.Cart;
import ru.zakharov.market.utils.CartPostBody;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;

@RestController
@RequestMapping("/api")
public class ShoppingCartRestController {
    private ShoppingCartService shoppingCartService;

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/cart_items")
    public Cart getCart(HttpServletRequest httpServletRequest) {
        return shoppingCartService.getCurrentCart(httpServletRequest.getSession());
    }

    @PostMapping("/cart_items")
    public Cart addProductToCartById(HttpServletRequest httpServletRequest, @RequestParam(name = "id") Long id) {
        shoppingCartService.addToCart(httpServletRequest.getSession(), id);
        return shoppingCartService.getCurrentCart(httpServletRequest.getSession());
    }

    @PostMapping(value = "/cart_items_quantity")
    public Cart setProductQuantity(HttpServletRequest httpServletRequest, @RequestBody CartPostBody cartPostBody) {
        shoppingCartService.setProductQuantity(httpServletRequest.getSession(), cartPostBody.getId(), cartPostBody.getQuantity());
        return shoppingCartService.getCurrentCart(httpServletRequest.getSession());
    }

    @DeleteMapping("/cart_items/{id}")
    public Cart deleteProductFromCartById(HttpServletRequest httpServletRequest, @PathVariable(name = "id") Long id) {
        shoppingCartService.removeFromCart(httpServletRequest.getSession(), id);
        return shoppingCartService.getCurrentCart(httpServletRequest.getSession());
    }

//    @GetMapping("/cart/remove/{id}")
//    public String removeProductFromCartById(Model model, HttpServletRequest httpServletRequest, @PathVariable(name = "id") Long id) {
//        shoppingCartService.removeFromCart(httpServletRequest.getSession(), id);
//        return "redirect:/shop/cart";
//    }
}
