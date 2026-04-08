package com.productManagemant.ProductManagemant.Controller;

import com.productManagemant.ProductManagemant.Dto.ResponseDto;
import com.productManagemant.ProductManagemant.Model.Orders;
import com.productManagemant.ProductManagemant.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {
@Autowired
private OrderService orderService;
@PostMapping
public ResponseDto createOrder(@RequestBody Orders order){
    ResponseDto responseDto;
    try {
        orderService.createOrder(order);
        responseDto =new ResponseDto<>("Order Created",true,null);
    } catch (Exception e) {
        responseDto =new ResponseDto<>(e.getMessage()+"Failed To Create Order",false,null);
    }
    return responseDto;
}
    @PostMapping("payment")
    public void makePayment(@RequestParam("oid") Integer oid){
        try{
            orderService.makePayment(oid);
        }catch (Exception e){

        }
    }
}
