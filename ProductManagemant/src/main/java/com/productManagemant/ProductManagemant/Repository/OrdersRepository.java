package com.productManagemant.ProductManagemant.Repository;

import com.productManagemant.ProductManagemant.Model.OrderItems;
import com.productManagemant.ProductManagemant.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Integer> {
    @Query(value = "select mobile_no from orders where id=:oid",nativeQuery = true)
    public String getNo(Integer oid);
}
