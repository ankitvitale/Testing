package com.greenGiftECommerce.GreenGiftECommerce.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;
    private String description;
    private String category;
    private double price;
    private int qty;
    private String pickuploaction;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> productImages;

    public Product() {}

    public Product(String name, String description, String category, double price, int qty, String pickuploaction, List<ProductImage> productImages) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.qty = qty;
        this.pickuploaction = pickuploaction;
        this.productImages = productImages;
    }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
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
    public String getPickuploaction() { return pickuploaction; }
    public void setPickuploaction(String pickuploaction) { this.pickuploaction = pickuploaction; }
    public List<ProductImage> getProductImages() { return productImages; }
    public void setProductImages(List<ProductImage> productImages) { this.productImages = productImages; }


}
