package com.greenGiftECommerce.GreenGiftECommerce.Controller;

import com.greenGiftECommerce.GreenGiftECommerce.DTO.CartDto;
import com.greenGiftECommerce.GreenGiftECommerce.Repository.BannerRepository;
import com.greenGiftECommerce.GreenGiftECommerce.Service.CartService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;
    @PostMapping("/add/{productId}")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<?> addToCart(@PathVariable Long productId,
                                       @RequestParam int quantity,
                                       @AuthenticationPrincipal UserDetails userDetails) {
        try {
            return ResponseEntity.ok(cartService.addToCart(productId, quantity, userDetails.getUsername()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/remove/{productId}")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<CartDto> removeFromCart(@PathVariable Long productId,
                                                  @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(cartService.removeFromCart(productId, userDetails.getUsername()));
    }
    @GetMapping("/cart")
        @PreAuthorize("hasRole('User')")
    public ResponseEntity<?> getUserCart(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            return ResponseEntity.ok(cartService.getUserCart(userDetails.getUsername()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

}
