package com.greenGiftECommerce.GreenGiftECommerce.Repository;

import com.greenGiftECommerce.GreenGiftECommerce.Entity.Product1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Product1Repository extends JpaRepository<Product1,Long> {
    @Query("SELECT p FROM Product1 p JOIN FETCH p.variants")
    List<Product1> findAllWithVariants();

}
