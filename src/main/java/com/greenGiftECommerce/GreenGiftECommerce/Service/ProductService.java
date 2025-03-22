package com.greenGiftECommerce.GreenGiftECommerce.Service;


import com.greenGiftECommerce.GreenGiftECommerce.DTO.ProducDiscounttDto;
import com.greenGiftECommerce.GreenGiftECommerce.DTO.ProductDto;
import com.greenGiftECommerce.GreenGiftECommerce.DTO.ResponseDTO.ProductResponseDto;
import com.greenGiftECommerce.GreenGiftECommerce.DTO.ResponseDTO.VariantResponseDto;
import com.greenGiftECommerce.GreenGiftECommerce.Entity.Banner;
import com.greenGiftECommerce.GreenGiftECommerce.Entity.Product;
import com.greenGiftECommerce.GreenGiftECommerce.Entity.Product1;
import com.greenGiftECommerce.GreenGiftECommerce.Entity.ProductImage;
import com.greenGiftECommerce.GreenGiftECommerce.Repository.BannerRepository;
import com.greenGiftECommerce.GreenGiftECommerce.Repository.Product1Repository;
import com.greenGiftECommerce.GreenGiftECommerce.Repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private BannerRepository bannerRepository;


    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Product1Repository product1Repository;

    @Autowired
    private ImageService imageService;

    public ProductDto addProduct(ProductDto productDto, List<MultipartFile> images) throws IOException {
        List<ProductImage> productImages = new ArrayList<>();

        if (images != null && !images.isEmpty()) {
            for (MultipartFile image : images) {
                String imageUrl = imageService.uploadFileToSpace(image);
                productImages.add(new ProductImage(imageUrl, null));
            }
        }

        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setQty(productDto.getQty());
        product.setPickuploaction(productDto.getPickupLocation());

        for (ProductImage img : productImages) {
            img.setProduct(product);
        }

        product.setProductImages(productImages);

        Product savedProduct = productRepository.save(product);
        return new ProductDto(savedProduct);
    }

//
//    public List<ProductDto> getAllProducts() {
//        List<Product> products = productRepository.findAll();
//        List<ProductDto> productDtos = new ArrayList<>();
//
//        for (Product product : products) {
//            Optional<Banner> bannerOpt = bannerRepository.findByCategoryIgnoreCase(product.getCategory());
//
//            if (bannerOpt.isPresent()) {
//                System.out.println("Banner found for category: " + product.getCategory());
//            } else {
//                System.out.println("No banner found for category: " + product.getCategory());
//            }
//
//            double discount = bannerOpt.map(Banner::getDiscount).orElse(0.0);
//            double discountedPrice = product.getPrice() - (product.getPrice() * discount / 100);
//
//            ProductDto productDto = new ProductDto(product);
//            productDto.setDiscountedPrice(discountedPrice);
//            productDtos.add(productDto);
//        }
//        return productDtos;
//    }



    private ProducDiscounttDto applyDiscount(Product product) {
        Banner banner = bannerRepository.findByCategory(product.getCategory()).orElse(null);
        double discount = (banner != null) ? banner.getDiscount() : 0;
        double discountedPrice = product.getPrice() - (product.getPrice() * discount / 100);
        return new ProducDiscounttDto(product, discountedPrice);


    }

//    public ProductResponseDto getProductById(Long id) {
//        return product1Repository.findById(id)
//                .map(product -> new ProductResponseDto(
//                        product.getId(),
//                        product.getName(),
//                        product.getDescription(),
//                        product.getCategory(),
//                        product.getPickupLocation(),
//                        Optional.ofNullable(product.getVariants())
//                                .orElse(Collections.emptyList())
//                                .stream()
//                                .map(variant -> new VariantResponseDto(
//                                        variant.getId(),
//                                        variant.getColor(),
//                                        variant.getPrice(),
//                                        variant.getQty(),
//                                        variant.getSize(),
//                                        variant.getImageUrls()
//                                ))
//                                .collect(Collectors.toList())
//                ))
//                .orElseThrow(() -> new EntityNotFoundException("Product ID " + id + " Not found"));
//    }





    private ProductDto convertToDto(Product product) {
        double discountedPrice = calculateDiscountedPrice(product);
        return new ProductDto(
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getPrice(),
                product.getQty(),
                product.getPickuploaction(),
                product.getProductImages().stream().map(ProductImage::getImageUrl).toList()
        );
    }

    private double calculateDiscountedPrice(Product product) {
        double discount = getDiscountForCategory(product.getCategory());
        return product.getPrice() - (product.getPrice() * discount / 100);
    }

    private double getDiscountForCategory(String category) {
        return 0; // Replace with actual logic
    }

    public ProductDto updateProduct(Long id, ProductDto productDto, List<MultipartFile> images) throws IOException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product ID " + id + " Not found"));

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setQty(productDto.getQty());
        product.setPickuploaction(productDto.getPickupLocation());

        // Handle Image Updates
        if (images != null && !images.isEmpty()) {
            List<ProductImage> productImages = new ArrayList<>();
            for (MultipartFile image : images) {
                String imageUrl = imageService.uploadFileToSpace(image);
                productImages.add(new ProductImage(imageUrl, product));
            }
            product.getProductImages().clear();
            product.getProductImages().addAll(productImages);
        }

        Product updatedProduct = productRepository.save(product);
        return new ProductDto(updatedProduct);
    }



    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product ID " + id + " Not found");
        }
        productRepository.deleteById(id);
    }

//    private final Product1Repository product1Repository;
//    private final BannerService bannerService;
//
//    @Autowired
//    public ProductService(Product1Repository product1Repository, BannerService bannerService) {
//        this.product1Repository = product1Repository;
//        this.bannerService = bannerService;
//    }

    public List<ProductResponseDto> getAllProducts() {
        List<Product1> products = product1Repository.findAll();
        List<ProductResponseDto> productDtos = new ArrayList<>();

        for (Product1 product : products) {
            Optional<Banner> bannerOpt = bannerRepository.findByCategoryIgnoreCase(product.getCategory());
            double discountPercentage = bannerOpt.map(Banner::getDiscount).orElse(0.0);

            List<VariantResponseDto> variantDtos = product.getVariants().stream().map(variant ->
                    new VariantResponseDto(
                            variant.getId(),
                            variant.getColor(),
                            variant.getPrice(),
                            calculateDiscountedPrice(variant.getPrice(), discountPercentage), // Discount applied here
                            variant.getQty(),
                            variant.getSize(), // Assuming size might be null
                            variant.getImageUrls()
                    )
            ).collect(Collectors.toList());

            ProductResponseDto productDto = new ProductResponseDto(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getCategory(),
                    product.getPickupLocation(),
                    variantDtos // Removed discountedPrice at product level
            );

            productDtos.add(productDto);
        }

        return productDtos;
    }

    // Utility method to calculate discounted price
    private double calculateDiscountedPrice(double price, double discountPercentage) {
        return price - (price * discountPercentage / 100);
    }


    public ProductResponseDto getProductById(Long id) {
        Product1 product = product1Repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product ID " + id + " Not found"));

        Optional<Banner> bannerOpt = bannerRepository.findByCategoryIgnoreCase(product.getCategory());
        double discountPercentage = bannerOpt.map(Banner::getDiscount).orElse(0.0);

        List<VariantResponseDto> variantDtos = product.getVariants().stream().map(variant ->
                new VariantResponseDto(
                        variant.getId(),
                        variant.getColor(),
                        variant.getPrice(),
                        calculateDiscountedPrice(variant.getPrice(), discountPercentage),
                        variant.getQty(),
                        variant.getSize(), // Handles null size
                        variant.getImageUrls()
                )
        ).collect(Collectors.toList());

        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getPickupLocation(),
                variantDtos // Discount is applied at the variant level
        );
    }
}

