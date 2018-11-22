package ru.zakharov.market.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zakharov.market.entities.Product;
import ru.zakharov.market.searches.ProductSearch;
import ru.zakharov.market.services.ProductService;

import java.util.List;


@Controller
public class MainController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/")
    public String showHomePage(Model model) {
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("productList", productList);
        model.addAttribute("search", new ProductSearch());
        return "index";
    }

    @PostMapping(value = "/search")
    public String search(Model model,
                         @ModelAttribute ProductSearch productSearch) {
        List<Product> productList = productService.getProductsByTitleContains(productSearch.getSearchQuery());
        model.addAttribute("productList", productList);
        model.addAttribute("search", new ProductSearch());
        return "index";
    }

    @RequestMapping("favicon.ico")
    String favicon() {
        return "forward:/resources/favicon.ico";
    }
}
