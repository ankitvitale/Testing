package com.greenGiftECommerce.GreenGiftECommerce.Service;


import com.greenGiftECommerce.GreenGiftECommerce.DTO.ShippingAddressDTO;
import com.greenGiftECommerce.GreenGiftECommerce.Entity.Cart;
import com.greenGiftECommerce.GreenGiftECommerce.Entity.ShippingAddress;
import com.greenGiftECommerce.GreenGiftECommerce.Repository.CartRepository;
import com.greenGiftECommerce.GreenGiftECommerce.Repository.ShippingAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingAddressService {
    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    @Autowired
    private CartRepository cartRepository;

    public ShippingAddress saveShippingAddress(Long cartId, ShippingAddressDTO shippingAddressDTO) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setName(shippingAddressDTO.getName());
        shippingAddress.setPhone(shippingAddressDTO.getPhone());
        shippingAddress.setAddress(shippingAddressDTO.getAddress());
        shippingAddress.setCity(shippingAddressDTO.getCity());
        shippingAddress.setZipCode(shippingAddressDTO.getZipCode());
        shippingAddress.setCountry(shippingAddressDTO.getCountry());
        shippingAddress.setCart(cart);

        return shippingAddressRepository.save(shippingAddress);
    }

}
