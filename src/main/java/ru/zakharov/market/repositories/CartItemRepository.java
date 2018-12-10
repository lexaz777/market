package ru.zakharov.market.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.zakharov.market.entities.CartItem;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem,Integer> {
}
