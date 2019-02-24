package ru.zakharov.market.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.zakharov.market.entities.Product;
import ru.zakharov.market.repositories.ProductPaginatedRepository;
import ru.zakharov.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private ProductPaginatedRepository productPaginatedRepository;


    @Autowired
    public void setProductPaginatedRepository(ProductPaginatedRepository productPaginatedRepository) {
        this.productPaginatedRepository = productPaginatedRepository;
    }

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

    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        return null;
    }

    public void saveProduct(Product product) {
        if (product == null) return;
        productRepository.save(product);
    }

    public List<Product> getProductsByPriceRange(int min, int max) {
        return productRepository.findProductsByPriceBetween(min,max);
    }

    public List<Product> getProductsByPriceRangeAndPage(Pageable pageable, int min, int max) {
        return productPaginatedRepository.findProductsByPriceBetween(min,max,pageable);
    }

    public int getCountOfProducts() {
        return productRepository.findAll().size();
    }

}
