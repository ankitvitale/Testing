package com.greenGiftECommerce.GreenGiftECommerce.Repository;


import com.greenGiftECommerce.GreenGiftECommerce.Entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categories,Long> {
}
