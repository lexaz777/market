package ru.zakharov.market.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.zakharov.market.entities.Category;
import ru.zakharov.market.entities.Product;
import ru.zakharov.market.searches.ProductSearch;
import ru.zakharov.market.services.CategoryService;
import ru.zakharov.market.services.FileSystemStorageService;
import ru.zakharov.market.services.ProductService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private ProductService productService;
    private CategoryService categoryService;
    private FileSystemStorageService storageService;

    @Autowired
    public void setStorageService(FileSystemStorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping("")
    public String adminHome(Model model) {
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("productList", productList);
        model.addAttribute("search", new ProductSearch());
        return "admin-panel";
    }

    @RequestMapping("/products")
    public String listProducts(Model model) {
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("productList", productList);
        model.addAttribute("search", new ProductSearch());
        return "admin-panel";
    }

    @RequestMapping("/products/add")
    public String addProduct(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("product", new Product());
        model.addAttribute("search", new ProductSearch());
        model.addAttribute("categories", categories);
        return "product-edit";
    }

    @RequestMapping("/products/edit")
    public String editProduct(Model model, @RequestParam int id) {
        List<Category> categories = categoryService.getAllCategories();
        Optional<Product> product = productService.getProductById(id);
        if (!product.isPresent()) return "redirect:/admin/products";
        model.addAttribute("product", product.get());
        model.addAttribute("search", new ProductSearch());
        model.addAttribute("categories", categories);
        return "product-edit";
    }

    @RequestMapping("/products/save")
    public String saveProduct(Category category, Product product) {
        if (category == null) {
            return "redirect:/admin/products/add";
        }
        productService.saveProduct(product);
        return "redirect:/admin/products/";
    }

    @PostMapping("/products/uploadFile")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, int id) {
        storageService.store(file);
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            Product theProduct = product.get();
            theProduct.setPhotoName(file.getOriginalFilename());
            productService.saveProduct(theProduct);
        }
        return "redirect:/admin/products/edit?id=" + id;
    }
}
