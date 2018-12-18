package ru.zakharov.market.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zakharov.market.entities.Product;
import ru.zakharov.market.searches.ProductSearch;
import ru.zakharov.market.services.ProductService;
import ru.zakharov.market.services.ShoppingCartService;
import ru.zakharov.market.utils.Cart;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
@SessionAttributes("shoppingCart")
public class CartController {
    private ProductService productService;
    private ShoppingCartService shoppingCartService;

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute(name = "shoppingCart")
    public Cart cart() {
        return new Cart();
    }

    @GetMapping("/show")
    public String cartPage(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("search", new ProductSearch());
        model.addAttribute("shoppingCart", shoppingCartService.getCurrentCart(httpServletRequest.getSession()));
        return "cart";
    }

    @GetMapping("/show-vue")
    public String vueCartPage(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("search", new ProductSearch());
        model.addAttribute("shoppingCart", shoppingCartService.getCurrentCart(httpServletRequest.getSession()));
        return "cart-vue";
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
    public String addToCart(@RequestParam Long id, Model model,
                            @ModelAttribute("shoppingCart") Cart cart) {
        model.addAttribute("search", new ProductSearch());
        Product product = productService.getProductById(id);
        if (product != null) {
            cart.addToCart(product);
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
    public String removeFromCart(@RequestParam Long id, Model model,
                                 @ModelAttribute("shoppingCart") Cart cart) {
        model.addAttribute("search", new ProductSearch());
        Product product = productService.getProductById(id);
        if (product != null) {
            cart.removeFromCart(product);
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
