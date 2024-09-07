package com.api.product_webflux.dto;

public class ProductResponseDTO {
    private int ProductId;
    private String Name;
    private double Price;

    public ProductResponseDTO(int productId, String name, double price) {
        ProductId = productId;
        Name = name;
        Price = price;
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
        return "ProductResponseDTO{" +
                "ProductId=" + ProductId +
                ", Name='" + Name + '\'' +
                ", Price=" + Price +
                '}';
    }
}
