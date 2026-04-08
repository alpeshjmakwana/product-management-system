package com.productManagemant.ProductManagemant.Controller;

import com.productManagemant.ProductManagemant.Dto.ResponseDto;
import com.productManagemant.ProductManagemant.Model.Product;
import com.productManagemant.ProductManagemant.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    //add product
    public ResponseDto addProduct(@RequestBody Product product){
        ResponseDto responseDto;
        try {
            productService.addProduct(product);
            responseDto =new ResponseDto("Product added succesfully",true,null);
        }catch (Exception e){
            responseDto =new ResponseDto<>("Error create the product",false,null);
        }
        return responseDto;
    }
    @PostMapping("add_all")
    public ResponseDto addMultipleProduct (@RequestBody List<Product>products){
        ResponseDto responseDto;
        try {
            productService.addMultipleProduct(products);
            responseDto =new ResponseDto("Product added successfully",true,null);
        } catch (Exception e) {
            responseDto =new ResponseDto("Error creating the products",false,null);
        }
        return responseDto;
    }
    @GetMapping("get-by-id")
    public Product getProduct(@PathVariable Integer id){
        return productService.getProductById(id);
    }

    @PutMapping("update")
    public ResponseDto updateProduct(@RequestBody Product product){
        try {
            productService.updateProduct(product);
            return new ResponseDto("Product Updated Successfully",true,null);
        } catch (Exception e) {
            return new ResponseDto("Failed To Update Product",false,null);
        }
    }
    @DeleteMapping("{id}")
    public String deleteProduct(@PathVariable Integer id){
        return productService.deleteProduct(id);
    }
}
