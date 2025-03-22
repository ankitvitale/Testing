package com.greenGiftECommerce.GreenGiftECommerce.Entity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user; // ✅ Corrected field name

    public Cart() {}

    public Cart(User user) { // ✅ Corrected constructor
        this.user = user;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public List<CartItem> getCartItems() { return cartItems; }
    public void setCartItems(List<CartItem> cartItems) { this.cartItems = cartItems; }

    public User getUser() { return user; } // ✅ Corrected getter name
    public void setUser(User user) { this.user = user; } // ✅ Corrected setter name

    public void addCartItem(CartItem cartItem) { cartItems.add(cartItem); }
    public void removeCartItem(CartItem cartItem) { cartItems.remove(cartItem); }
}
