package com.greenGiftECommerce.GreenGiftECommerce.Repository;

import com.greenGiftECommerce.GreenGiftECommerce.Entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {
    Optional<Banner> findByCategory(String category);

    <T> Optional<T> findByCategoryIgnoreCase(String category);
}
