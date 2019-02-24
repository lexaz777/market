package ru.zakharov.market.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.zakharov.market.entities.Product;

import java.util.List;

@Repository
public interface ProductPaginatedRepository extends PagingAndSortingRepository<Product, Long> {
    public List<Product> findProductsByPriceBetween(int min, int max, Pageable pageable);
}
