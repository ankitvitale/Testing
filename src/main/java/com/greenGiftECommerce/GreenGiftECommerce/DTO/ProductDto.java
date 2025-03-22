package com.greenGiftECommerce.GreenGiftECommerce.DTO;

import com.greenGiftECommerce.GreenGiftECommerce.Entity.Product;
import com.greenGiftECommerce.GreenGiftECommerce.Entity.ProductImage;

import java.util.List;

public class ProductDto {
    private String name;
    private String description;
    private String category;
    private double price;
    private double discountedPrice; // Added field

    private int qty;
    private String pickupLocation;
    private List<String> imgUrls;

    public ProductDto() {}

    public ProductDto(String name, String description, String category, double price, int qty, String pickupLocation, List<String> imgUrls) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.qty = qty;
        this.pickupLocation = pickupLocation;
        this.imgUrls = imgUrls;
    }

    public ProductDto(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.qty = product.getQty();
        this.pickupLocation = product.getPickuploaction();
        this.imgUrls = product.getProductImages().stream().map(ProductImage::getImageUrl).toList();
    }


    public List<String> getImgUrls() { return imgUrls; }
    public void setImgUrls(List<String> imgUrls) { this.imgUrls = imgUrls; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }
    public String getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }
    public double getDiscountedPrice() { return discountedPrice; }
    public void setDiscountedPrice(double discountedPrice) { this.discountedPrice = discountedPrice; }

}