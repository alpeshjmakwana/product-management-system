package com.productManagemant.ProductManagemant.Repository;

import com.productManagemant.ProductManagemant.Model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItems,Integer> {
    @Query(value = "select * from order_items where order_id = :oid", nativeQuery = true)
    public List<OrderItems> getListOfItems(Integer oid);
}