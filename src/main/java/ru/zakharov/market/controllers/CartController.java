package ru.zakharov.market.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.zakharov.market.entities.Product;
import ru.zakharov.market.searches.ProductSearch;
import ru.zakharov.market.services.ProductService;
import ru.zakharov.market.utils.Cart;

import java.util.Optional;

@Controller
@RequestMapping("/cart")
@SessionAttributes("shoppingCart")
public class CartController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute(name = "shoppingCart")
    public Cart cart() {
        return new Cart();
    }

    @RequestMapping("/list")
    public String listCart(Model model,
                           @ModelAttribute("shoppingCart") Cart cart) {
        model.addAttribute("search", new ProductSearch());

        if (!cart.getCartItems().isEmpty()) {
            model.addAttribute("shoppingCart", cart);
        } else {
            model.addAttribute("shoppingCart", new Cart());
        }

        model.addAttribute("shoppingCart");
        return "cart";
    }

    @RequestMapping("/add")
    public String addToCart(@RequestParam int id, Model model,
                            @ModelAttribute("shoppingCart") Cart cart) {
        model.addAttribute("search", new ProductSearch());
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            cart.addToCart(product.get());
            if (!cart.getCartItems().isEmpty()) {
                model.addAttribute("shoppingCart", cart);
            } else {
                model.addAttribute("shoppingCart", new Cart());
            }
            return "redirect:/cart/list";
        } else
            return "index";
    }

    @RequestMapping("/remove")
    public String removeFromCart(@RequestParam int id, Model model,
                                 @ModelAttribute("shoppingCart") Cart cart) {
        model.addAttribute("search", new ProductSearch());
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            cart.removeFromCart(product.get());
            if (!cart.getCartItems().isEmpty()) {
                model.addAttribute("shoppingCart", cart);
            } else {
                model.addAttribute("shoppingCart", new Cart());
            }
            return "redirect:/cart/list";
        } else
            return "index";
    }
}
