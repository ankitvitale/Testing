package com.greenGiftECommerce.GreenGiftECommerce.Repository;

import com.greenGiftECommerce.GreenGiftECommerce.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
