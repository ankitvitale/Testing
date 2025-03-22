package com.greenGiftECommerce.GreenGiftECommerce.DTO;

import com.greenGiftECommerce.GreenGiftECommerce.Entity.CartItem;

public class CartItemDto {
    private Long productId;
    private String productName;
    private int quantity;
    private double price;
    private double totalPrice;
    private double discountAmount;

    public CartItemDto() {}

    public CartItemDto(CartItem cartItem) {
        this.productId = cartItem.getProduct().getProductId();
        this.productName = cartItem.getProduct().getName();
        this.quantity = cartItem.getQuantity();
        this.price = cartItem.getProduct().getPrice();
        this.totalPrice = cartItem.getQuantity() * cartItem.getProduct().getPrice();
    }

//    public CartItemDto(CartItem cartItem) {
//        this.productId = cartItem.getProduct().getProductId();
//        this.productName = cartItem.getProduct().getName();
//        this.quantity = cartItem.getQuantity();
//        this.totalPrice = cartItem.getProduct().getPrice() * quantity;
//
//       // double discount = cartItem.getProduct().; // Get product discount
//        this.discountAmount = totalPrice * (discount / 100); // Calculate discount
//    }
    public Long getProductId() { return productId; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public double getTotalPrice() { return totalPrice; }

    public void setProductId(Long productId) { this.productId = productId; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }
}
