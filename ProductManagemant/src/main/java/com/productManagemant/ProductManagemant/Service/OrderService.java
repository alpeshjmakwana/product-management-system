package com.productManagemant.ProductManagemant.Service;

import com.productManagemant.ProductManagemant.Model.OrderItems;
import com.productManagemant.ProductManagemant.Model.Orders;
import com.productManagemant.ProductManagemant.Model.Product;
import com.productManagemant.ProductManagemant.Repository.OrderItemRepository;
import com.productManagemant.ProductManagemant.Repository.OrdersRepository;
import com.productManagemant.ProductManagemant.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    static Integer count;
    static{
        count =1;
    }
    @Autowired
    OrdersRepository ordersRepository;
@Autowired
    OrderItemRepository orderItemRepository;
@Autowired
    ProductRepository productRepository;
@Autowired
MessageService messageService;
    public void createOrder(Orders order)throws RuntimeException {
    checkOrder(order.getOrderItems());
    Orders orders =ordersRepository.save(order);
        for (OrderItems orderItems :order.getOrderItems()) {
            orderItems.setOrder(orders);
            orderItemRepository.save(orderItems);
        }
    }
    public void makePayment(Integer oid) throws Exception{
        String num = ordersRepository.getNo(oid);
        if(count%4==0){
            messageService.sendMessage(num,"Order has been cancelled due to payment failed","");
            messageService.sendMessage(num,"Order has been cancelled due to payment failed","whatsapp");

        }else{
            double amt = updateStock(oid);
            messageService.sendMessage(num,"Payment received of $"+amt,"");
            messageService.sendMessage(num,"Payment received of $"+amt,"whatsapp");
        }
        count++;
    }

    public boolean checkOrder(List<OrderItems> items) throws RuntimeException{
        for(OrderItems oit:items){
            Integer pid = oit.getProduct().getId();
            Integer totalStock = productRepository.getTotalStock(pid);
            Integer currNeedStock = oit.getProductCount();
            if(totalStock < currNeedStock){
                throw new RuntimeException("Product not available");
            }
        }
        return true;
    }

    @Value("${twilio.phone.privateno}")
    private String privateNo;
    public double updateStock(Integer oid) throws RuntimeException{
        double amt=0;
        List<OrderItems> o = orderItemRepository.getListOfItems(oid);
        for(OrderItems oit : o){
            int pid = oit.getProduct().getId();
            Product p = productRepository.findById(pid).orElse(null);
            if(p != null){
                p.setQuntity(p.getQuntity()-oit.getProductCount());
                amt+=oit.getProductCount()*p.getMrp();
                amt+=amt*p.getGst()/100;
            }
            if(p.getQuntity() <= p.getThreshold()){
                String msg=p.getId()+" "+p.getProductName()+" reached threshold limit.Restock the product again";
                messageService.sendMessage(privateNo,msg,"");
                messageService.sendMessage(privateNo,msg,"whatsapp");
            }
            productRepository.save(p);
        }
        Orders order=ordersRepository.findById(oid).orElse(null);
        if(order != null){
            order.setStatus("DONE");
            ordersRepository.save(order);
        }
        return amt;

    }

}
