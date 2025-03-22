package com.greenGiftECommerce.GreenGiftECommerce.DTO;

import com.greenGiftECommerce.GreenGiftECommerce.Entity.Product;

public class ProducDiscounttDto {
    private String name;
    private String category;
    private double price;
    private double discountedPrice; // Price after applying category discount

    public ProducDiscounttDto(Product product, double discountedPrice) {
        this.name = product.getName();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.discountedPrice = discountedPrice;
    }

    // Getters and Setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
}