package ru.zakharov.market.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.zakharov.market.entities.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Integer> {
    List<Category> findAll();
}
