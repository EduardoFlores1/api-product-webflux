package com.api.product_webflux.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "Products")
public class ProductEntity {

    @Id
    private int ProductId;
    private String Name;
    private double Price;

    public ProductEntity() {}

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "ProductId=" + ProductId +
                ", Name='" + Name + '\'' +
                ", Price=" + Price +
                '}';
    }
}
