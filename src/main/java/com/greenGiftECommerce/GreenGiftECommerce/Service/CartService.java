package com.greenGiftECommerce.GreenGiftECommerce.Service;


import com.greenGiftECommerce.GreenGiftECommerce.DTO.CartDto;
import com.greenGiftECommerce.GreenGiftECommerce.Entity.*;
import com.greenGiftECommerce.GreenGiftECommerce.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BannerRepository bannerRepository;

    public CartDto addToCart(Long productId, int quantity, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + email + " not found"));

        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart(user);
            return cartRepository.save(newCart);
        });

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        if (quantity > product.getQty()) {
            throw new IllegalArgumentException("Requested quantity exceeds available stock.");
        }

        CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product)
                .orElseGet(() -> new CartItem(cart, product, 0));

        int totalQuantity = cartItem.getQuantity() + quantity;
        if (totalQuantity > product.getQty()) {
            throw new IllegalArgumentException("Total quantity in cart exceeds available stock.");
        }

        Optional<Banner> bannerOpt = bannerRepository.findByCategoryIgnoreCase(product.getCategory());
        double discount = bannerOpt.map(Banner::getDiscount).orElse(0.0);
        double discountedPrice = product.getPrice() - (product.getPrice() * discount / 100);

        cartItem.setQuantity(totalQuantity);
        cartItem.setDiscount(discount);
        cartItem.setDiscountedPrice(discountedPrice * totalQuantity);
        cart.addCartItem(cartItem);

        cartRepository.save(cart);

        return new CartDto(cart);
    }


    public CartDto removeFromCart(Long productId, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + email + " not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found for user"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product)
                .orElseThrow(() -> new EntityNotFoundException("Product not in cart"));

        cart.getCartItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
        cartRepository.save(cart);

        return new CartDto(cart);
    }

    public CartDto getUserCart(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        return new  CartDto(cart, bannerRepository);
    }



}
