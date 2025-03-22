package com.greenGiftECommerce.GreenGiftECommerce.Repository;

import com.greenGiftECommerce.GreenGiftECommerce.Entity.Cart;
import com.greenGiftECommerce.GreenGiftECommerce.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
  //  Optional<Cart> findByEmail(User email);


    Optional<Cart> findByUser(User user); // ✅ Corrected query method

}