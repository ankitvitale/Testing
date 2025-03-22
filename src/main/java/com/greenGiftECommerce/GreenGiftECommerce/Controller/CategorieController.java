package com.greenGiftECommerce.GreenGiftECommerce.Controller;


import com.greenGiftECommerce.GreenGiftECommerce.DTO.CategoryDTO;
import com.greenGiftECommerce.GreenGiftECommerce.Entity.Categories;
import com.greenGiftECommerce.GreenGiftECommerce.Service.CategorieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CategorieController {

    @Autowired
    private CategorieService categorieService;
    @PostMapping("/category")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody Categories categories){
        CategoryDTO saveCategoryDTO=categorieService.createCategory(categories);
        return  new ResponseEntity<CategoryDTO>(saveCategoryDTO, HttpStatus.CREATED);
    }

    @GetMapping("/Allcategory")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categorieService.getAllCategories());
    }

    @GetMapping("/category/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categorieService.getCategoryById(id));
    }

    @PutMapping("/updateCategory/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody Categories categories) {
        CategoryDTO updatedCategory = categorieService.updateCategory(id, categories);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/deleteCategory{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categorieService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
