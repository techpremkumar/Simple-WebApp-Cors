package com.prem.demo.service;

import com.prem.demo.model.Product;
import com.prem.demo.repository.ProductRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Create
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Read all
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Read by ID
    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    // Update
    public Optional<Product> updateProduct(
            Integer id, Product updatedProduct) {

        return productRepository.findById(id)
                .map(existingProduct -> {

                    existingProduct.setName(updatedProduct.getName());
                    existingProduct.setDescription(updatedProduct.getDescription());
                    existingProduct.setBrand(updatedProduct.getBrand());
                    existingProduct.setPrice(updatedProduct.getPrice());
                    existingProduct.setCategory(updatedProduct.getCategory());
                    existingProduct.setReleaseDate(updatedProduct.getReleaseDate());
                    existingProduct.setAvailable(updatedProduct.isAvailable());
                    existingProduct.setQuantity(updatedProduct.getQuantity());

                    return productRepository.save(existingProduct);
                });
    }

    // Delete
    public boolean deleteProduct(Integer id) {

        if (!productRepository.existsById(id)) {
            return false;
        }

        productRepository.deleteById(id);
        return true;
    }
}