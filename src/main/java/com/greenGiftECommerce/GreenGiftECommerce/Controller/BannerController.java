package com.greenGiftECommerce.GreenGiftECommerce.Controller;

import com.greenGiftECommerce.GreenGiftECommerce.Entity.Banner;
import com.greenGiftECommerce.GreenGiftECommerce.Repository.BannerRepository;
import com.greenGiftECommerce.GreenGiftECommerce.Service.ImageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BannerController {
     @Autowired
     private ImageService imageService;
    @Autowired
    private BannerRepository bannerRepository;
    @PostMapping(value = "/addBanner", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Banner> addBanner(
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam("discount") double discount,
            @RequestParam("image") MultipartFile image) throws IOException {

        String imageUrl = imageService.uploadFileToSpace(image);

        Banner banner = new Banner();
        banner.setName(name);
        banner.setCategory(category);
        banner.setDiscount(discount);
        banner.setImage(imageUrl);

        return new ResponseEntity<>(bannerRepository.save(banner), HttpStatus.CREATED);
    }


    @GetMapping("/Banner/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Banner> getBannerById(@PathVariable Long id) {
        Banner banner = bannerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Banner with ID " + id + " not found"));
        return ResponseEntity.ok(banner);
    }
    // ✅ Get All Banners
    @GetMapping("/allBanner")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<Banner>> getAllBanners() {
        return ResponseEntity.ok(bannerRepository.findAll());
    }
    // ✅ Update Banner
    @PutMapping("/updateBanner/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Banner> updateBanner(@PathVariable Long id, @RequestBody Banner updatedBanner) {
        Banner banner = bannerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Banner with ID " + id + " not found"));

        banner.setName(updatedBanner.getName());
        banner.setImage(updatedBanner.getImage());
        banner.setCategory(updatedBanner.getCategory());
        banner.setDiscount(updatedBanner.getDiscount());

        return ResponseEntity.ok(bannerRepository.save(banner));
    }

    // ✅ Delete Banner
    @DeleteMapping("/deleteBanner/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> deleteBanner(@PathVariable Long id) {
        Banner banner = bannerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Banner with ID " + id + " not found"));

        bannerRepository.delete(banner);
        return ResponseEntity.ok(Map.of("message", "Banner deleted successfully"));
    }
}
