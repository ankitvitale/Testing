package com.greenGiftECommerce.GreenGiftECommerce.Repository;

import com.greenGiftECommerce.GreenGiftECommerce.Entity.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {
}