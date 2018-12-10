package ru.zakharov.market.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.zakharov.market.entities.ShopOrder;

@Repository
public interface ShopOrderRepository extends CrudRepository<ShopOrder,Long> {
    ShopOrder findById(int id);
}
