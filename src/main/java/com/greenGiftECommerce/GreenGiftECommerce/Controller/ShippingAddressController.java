package com.greenGiftECommerce.GreenGiftECommerce.Controller;

import com.greenGiftECommerce.GreenGiftECommerce.DTO.ShippingAddressDTO;
import com.greenGiftECommerce.GreenGiftECommerce.Entity.ShippingAddress;
import com.greenGiftECommerce.GreenGiftECommerce.Service.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipping")
public class ShippingAddressController {

    @Autowired
    private ShippingAddressService shippingAddressService;

    @PostMapping("/add/{cartId}")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<ShippingAddressDTO> addShippingAddress(
            @PathVariable Long cartId,
            @RequestBody ShippingAddressDTO shippingAddressDTO) {
        ShippingAddress savedAddress = shippingAddressService.saveShippingAddress(cartId, shippingAddressDTO);
        return ResponseEntity.ok(convertToDTO(savedAddress));
    }
    private ShippingAddressDTO convertToDTO(ShippingAddress shippingAddress) {
        ShippingAddressDTO dto = new ShippingAddressDTO();
        dto.setName(shippingAddress.getName());
        dto.setPhone(shippingAddress.getPhone());
        dto.setAddress(shippingAddress.getAddress());
        dto.setCity(shippingAddress.getCity());
        dto.setZipCode(shippingAddress.getZipCode());
        dto.setCountry(shippingAddress.getCountry());
        return dto;
    }

}