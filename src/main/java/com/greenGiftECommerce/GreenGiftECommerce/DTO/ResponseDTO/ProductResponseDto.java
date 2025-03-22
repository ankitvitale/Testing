package com.greenGiftECommerce.GreenGiftECommerce.DTO.ResponseDTO;

import java.util.List;

public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private String category;
    private String pickupLocation;
    private List<VariantResponseDto> variants;

    public ProductResponseDto(Long id, String name, String description, String category, String pickupLocation, List<VariantResponseDto> variants) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.pickupLocation = pickupLocation;
        this.variants = variants;
    }
// Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public List<VariantResponseDto> getVariants() {
        return variants;
    }

    public void setVariants(List<VariantResponseDto> variants) {
        this.variants = variants;
    }
}
