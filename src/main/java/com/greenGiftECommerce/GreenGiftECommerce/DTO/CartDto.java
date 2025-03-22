package com.greenGiftECommerce.GreenGiftECommerce.DTO;

import com.greenGiftECommerce.GreenGiftECommerce.Entity.Banner;
import com.greenGiftECommerce.GreenGiftECommerce.Entity.Cart;
import com.greenGiftECommerce.GreenGiftECommerce.Entity.Product;
import com.greenGiftECommerce.GreenGiftECommerce.Repository.BannerRepository;

import java.util.ArrayList;
import java.util.List;

public class CartDto {
    private Long cartId;
    private List<CartItemDto> cartItems = new ArrayList<>();
    private double totalCartPrice;
    private double totalDiscount;  // ✅ Add discount field
    private double finalPrice;
    private String user; // Added field for user identification

    public CartDto() {}

    public CartDto(Cart cart) {
        this.cartId = cart.getId();
        this.user = cart.getUser().getEmail(); // Fetching the associated user's email
        this.cartItems = cart.getCartItems().stream().map(CartItemDto::new).toList();
        this.totalCartPrice = cartItems.stream().mapToDouble(CartItemDto::getTotalPrice).sum();
        this.totalDiscount = cartItems.stream().mapToDouble(CartItemDto::getDiscountAmount).sum();
        this.finalPrice = totalCartPrice - totalDiscount;
    }

    public CartDto(Cart cart, BannerRepository bannerRepository) {
        this.cartId = cart.getId();
        this.user = cart.getUser().getEmail();

        if (cart.getCartItems() != null) {
            this.cartItems = cart.getCartItems().stream().map(cartItem -> {
                Product product = cartItem.getProduct();
                double discount = bannerRepository.findByCategoryIgnoreCase(product.getCategory())
                        .map(banner -> ((Banner) banner).getDiscount())
                        .orElse(0.0);

                double discountedPrice = product.getPrice() - (product.getPrice() * discount / 100);
                double totalDiscountAmount = (product.getPrice() - discountedPrice) * cartItem.getQuantity();

                CartItemDto cartItemDto = new CartItemDto(cartItem);
                cartItemDto.setDiscountAmount(totalDiscountAmount);
                cartItemDto.setTotalPrice(discountedPrice * cartItem.getQuantity());

                return cartItemDto;
            }).toList();

            this.totalCartPrice = cartItems.stream().mapToDouble(CartItemDto::getTotalPrice).sum();
            this.totalDiscount = cartItems.stream().mapToDouble(CartItemDto::getDiscountAmount).sum();
            this.finalPrice = totalCartPrice - totalDiscount;
        }
    }



    public Long getCartId() { return cartId; }
    public List<CartItemDto> getCartItems() { return cartItems; }
    public double getTotalCartPrice() { return totalCartPrice; }


    public void setCartId(Long cartId) { this.cartId = cartId; }
    public void setCartItems(List<CartItemDto> cartItems) { this.cartItems = cartItems; }
    public void setTotalCartPrice(double totalCartPrice) { this.totalCartPrice = totalCartPrice; }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }
}
