package com.productManagemant.ProductManagemant.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Orders {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
@Column(name = "customer_name")
private String customerName;
@Column(name = "mobile_no")
private String mobileNo;
@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
private List<OrderItems>orderItems;
private String status;
private Date date;
@PrePersist
    protected void onCreate(){this.date =new Date();}
}
