package com.productManagemant.ProductManagemant.Repository;

import com.productManagemant.ProductManagemant.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query(value = "select quantity from product where id=:pid",nativeQuery = true)
    public Integer getTotalStock(Integer pid);
}
