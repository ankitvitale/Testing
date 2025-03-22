package com.greenGiftECommerce.GreenGiftECommerce.Service;

import com.greenGiftECommerce.GreenGiftECommerce.DTO.CategoryDTO;
import com.greenGiftECommerce.GreenGiftECommerce.Entity.Categories;
import com.greenGiftECommerce.GreenGiftECommerce.Repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategorieService {
    @Autowired
    private CategorieRepository categorieRepository;

    public CategoryDTO createCategory(Categories categories) {
        Categories savedCategory = categorieRepository.save(categories);
        return new CategoryDTO(savedCategory.getCategoryId(), savedCategory.getCategoryName());
    }


    public List<CategoryDTO> getAllCategories() {
        return categorieRepository.findAll()
                .stream()
                .map(cat -> new CategoryDTO(cat.getCategoryId(), cat.getCategoryName()))
                .collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(Long id) {
        Categories category = categorieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return new CategoryDTO(category.getCategoryId(), category.getCategoryName());
    }

    public CategoryDTO updateCategory(Long id, Categories updatedCategory) {
        Categories existingCategory = categorieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        existingCategory.setCategoryName(updatedCategory.getCategoryName());
        Categories savedCategory = categorieRepository.save(existingCategory);
        return new CategoryDTO(savedCategory.getCategoryId(), savedCategory.getCategoryName());
    }

    public void deleteCategory(Long id) {
        Categories category = categorieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categorieRepository.delete(category);
    }
}
