package com.greenGiftECommerce.GreenGiftECommerce.Controller;

import com.greenGiftECommerce.GreenGiftECommerce.Entity.Admin;
import com.greenGiftECommerce.GreenGiftECommerce.Entity.JwtRequest;
import com.greenGiftECommerce.GreenGiftECommerce.Entity.JwtResponse;
import com.greenGiftECommerce.GreenGiftECommerce.Service.AuthService;
import com.greenGiftECommerce.GreenGiftECommerce.Service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }
    @PostMapping({"/registerAdmin"})
    public Admin registerNewAdmin(@RequestBody Admin admin) {
        return userService.registerAdmin(admin);
    }

    @PostMapping("/auth/login")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        return authService.createJwtToken(jwtRequest);
    }

}
