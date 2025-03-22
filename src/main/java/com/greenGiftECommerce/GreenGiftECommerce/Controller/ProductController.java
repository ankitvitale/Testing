package com.greenGiftECommerce.GreenGiftECommerce.Controller;

import com.greenGiftECommerce.GreenGiftECommerce.DTO.ProductDto;
import com.greenGiftECommerce.GreenGiftECommerce.DTO.RequestDTO.ProductRequest;
import com.greenGiftECommerce.GreenGiftECommerce.DTO.RequestDTO.VariantRequest;
import com.greenGiftECommerce.GreenGiftECommerce.DTO.ResponseDTO.ProductResponseDto;
import com.greenGiftECommerce.GreenGiftECommerce.DTO.ResponseDTO.VariantResponseDto;
import com.greenGiftECommerce.GreenGiftECommerce.Entity.Product1;
import com.greenGiftECommerce.GreenGiftECommerce.Entity.Variant;
import com.greenGiftECommerce.GreenGiftECommerce.Repository.Product1Repository;
import com.greenGiftECommerce.GreenGiftECommerce.Service.ImageService;
import com.greenGiftECommerce.GreenGiftECommerce.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ImageService imageService;

    @Autowired
    private Product1Repository product1Repository;




    @PostMapping(value = "/addProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Product1> addProduct(
            @RequestPart("product") ProductRequest productRequest,
            @RequestPart(value = "images", required = false) List<MultipartFile> images) throws IOException {

        Product1 product = new Product1();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setCategory(productRequest.getCategory());
        product.setPickupLocation(productRequest.getPickupLocation());

        List<Variant> variantList = new ArrayList<>();

        for (VariantRequest variantRequest : productRequest.getVariants()) {
            Variant variant = new Variant();
            variant.setColor(variantRequest.getColor());
            variant.setPrice(variantRequest.getPrice());
            variant.setQty(variantRequest.getQty());

            List<String> imageUrls = new ArrayList<>();
            if (images != null) {
                for (MultipartFile image : images) {
                    String imageUrl = imageService.uploadFileToSpace(image);
                    imageUrls.add(imageUrl);
                }
            }

            variant.setImageUrls(imageUrls);
            variant.setProduct1(product);
            variantList.add(variant);
        }

        product.setVariants(variantList);
        return new ResponseEntity<>(product1Repository.save(product), HttpStatus.CREATED);
    }


    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<ProductDto> addProduct(
            @RequestPart("product")   ProductDto productDto,
            @RequestPart("images") List<MultipartFile> images) throws IOException {
        ProductDto savedProduct = productService.addProduct(productDto, images);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/AllProduct")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Utility method to calculate discounted price
    private double calculateDiscountedPrice(double price, double discountPercentage) {
        return price - (price * discountPercentage / 100);
    }

// product add old one
//    @GetMapping("/AllProduct")
//    public ResponseEntity<List<ProductDto>> getAllProducts() {
//        List<ProductDto> products = productService.getAllProducts();
//        return ResponseEntity.ok(products);
//    }
//
    @GetMapping("/Product/{id}")
    public ResponseEntity<ProductResponseDto> getproductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));

    }

 //old code
//    @GetMapping("/Product/{id}")
//    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
//        return ResponseEntity.ok(productService.getProductById(id));
//    }

    @PutMapping(value = "/updateProduct/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable Long id,
            @RequestPart("product") ProductDto productDto,
            @RequestPart(value = "images", required = false) List<MultipartFile> images) throws IOException {
        ProductDto updatedProduct = productService.updateProduct(id, productDto, images);
        return ResponseEntity.ok(updatedProduct);
    }


    @DeleteMapping("/deleteProduct/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product ID " + id + " deleted successfully");
    }
}
