package com.productManagemant.ProductManagemant.Model;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "product_name")
    private String productName;
    private Double mrp;
    private Double gst;
    private Integer quntity;
    @Column(name = "cost_price")
    private Double costPrice;
    private Integer threshold;

    public Double getCostPrice() {return costPrice;}
    public void setCostPrice(Double costPrice) {this.costPrice = costPrice;}
    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public String getProductName() {return productName;}
    public void setProductName(String productName) {this.productName = productName;}
    public Double getMrp() {return mrp;}
    public void setMrp(Double mrp) {this.mrp = mrp;}
    public Double getGst() {return gst;}
    public void setGst(Double gst) {this.gst = gst;}
    public Integer getQuntity() {return quntity;}
    public void setQuntity(Integer quntity) {this.quntity = quntity;}
    public Integer getThreshold() {return threshold;}
    public void setThreshold(Integer threshold) {this.threshold = threshold;}
}
