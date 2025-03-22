package com.greenGiftECommerce.GreenGiftECommerce.Controller;

import com.greenGiftECommerce.GreenGiftECommerce.Entity.Admin;
import com.greenGiftECommerce.GreenGiftECommerce.Entity.User;
import com.greenGiftECommerce.GreenGiftECommerce.Service.AuthService;
import com.greenGiftECommerce.GreenGiftECommerce.Service.EmailService;
import com.greenGiftECommerce.GreenGiftECommerce.Service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;


    @Autowired
    private EmailService emailService;
    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }
    @PostMapping({"/registerUser"})
    public User registerNewAdmin(@RequestBody User user) {
        return userService.registeruser(user);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        userService.generateResetToken(email);
        return ResponseEntity.ok("Password reset link sent to your email.");
    }
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        userService.resetPassword(token, newPassword);
        return ResponseEntity.ok("Password reset successful.");
    }

}
