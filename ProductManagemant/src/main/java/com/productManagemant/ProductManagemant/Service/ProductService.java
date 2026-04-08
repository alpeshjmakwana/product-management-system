package com.productManagemant.ProductManagemant.Service;

import com.productManagemant.ProductManagemant.Model.Product;
import com.productManagemant.ProductManagemant.Repository.OrdersRepository;
import com.productManagemant.ProductManagemant.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void addMultipleProduct(List<Product> products) {
        productRepository.saveAll(products);
    }

    public Product getProductById(Integer id)throws RuntimeException {
        Product product =productRepository.findById(id).orElse(null);
        if(product==null){
            throw new RuntimeException("No Product Found");
        }
        return product;
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    public String deleteProduct(Integer id) {
        productRepository.deleteById(id);
        return "Product Deleted Successfully";
    }
}
