package com.greenGiftECommerce.GreenGiftECommerce.Repository;


import com.greenGiftECommerce.GreenGiftECommerce.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {

    Admin findByEmail(String email);

}