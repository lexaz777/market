package ru.zakharov.market.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zakharov.market.entities.Product;
import ru.zakharov.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByTitleContains(String title) {
        return productRepository.findProductsByTitleContains(title);
    }

    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    public void saveProduct(Product product) {
        if (product == null) return;
        productRepository.save(product);
    }

}
