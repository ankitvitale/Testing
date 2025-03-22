package com.greenGiftECommerce.GreenGiftECommerce.Repository;

import com.greenGiftECommerce.GreenGiftECommerce.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, String> {

}