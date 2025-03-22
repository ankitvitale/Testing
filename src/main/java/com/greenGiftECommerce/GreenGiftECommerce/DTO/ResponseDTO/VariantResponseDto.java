package com.greenGiftECommerce.GreenGiftECommerce.DTO.ResponseDTO;

import java.util.List;

public class VariantResponseDto {
    private Long id;
    private String color;
    private double price;
    private double discountedPrice;
    private int qty;
    private String size;
    private List<String> imageUrls;

    public VariantResponseDto(Long id, String color, double price, double discountedPrice, int qty, String size, List<String> imageUrls) {
        this.id = id;
        this.color = color;
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.qty = qty;
        this.size = size;
        this.imageUrls = imageUrls;
    }


// Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}