package com.prem.demo.controller;

import com.prem.demo.model.Product;
import com.prem.demo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Home
    @GetMapping("/home")
    public String home() {
        return "Welcome to E-Commerce Application";
    }

    // Create product
    // Add product with image
    @PostMapping(value = "/image", consumes = "multipart/form-data")
    public ResponseEntity<Product> addProductWithImage(
            @ModelAttribute Product product,
            @RequestPart("image") MultipartFile image)
            throws IOException {

        Product savedProduct =
                productService.addProduct(product, image);

        return ResponseEntity.status(201).body(savedProduct);
    }
    // Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {

        return ResponseEntity.ok(productService.getAllProducts());
    }

    // Get product by ID
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(
            @RequestParam String keyword) {

        List<Product> products =
                productService.searchProducts(keyword);

        System.out.println("Searching with " + keyword);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    // Update product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Integer id,
            @RequestBody Product product) {

        return productService.updateProduct(id, product)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // Fetch product image
    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Integer id) {

        return productService.getProductById(id)
                .filter(product -> product.getImageData() != null)
                .map(product -> ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(product.getImageType()))
                        .body(product.getImageData()))
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        boolean deleted = productService.deleteProduct(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}