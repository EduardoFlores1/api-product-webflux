package com.api.product_webflux.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "Products")
public class ProductEntity {

    @Id
    private int ProductId;
    private String Name;
    private double Price;

    private ProductEntity(String name, double price) {
        Name = name;
        Price = price;
    }
    private ProductEntity(int productId, String name, double price) {
        ProductId = productId;
        Name = name;
        Price = price;
    }


    // exponemos un metodo mas declarativo para el constructor
    public static ProductEntity createCTR(String name, double price) {
        return new ProductEntity(name, price);
    }
    public static ProductEntity updateCTR(int productId, String name, double price) {
        return new ProductEntity(productId, name, price);
    }

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
