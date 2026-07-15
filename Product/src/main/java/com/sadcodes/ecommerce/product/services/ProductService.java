package com.sadcodes.ecommerce.product.services;


import com.sadcodes.ecommerce.product.dto.ProductRequest;
import com.sadcodes.ecommerce.product.dto.ProductResponse;
import com.sadcodes.ecommerce.product.model.Product;
import com.sadcodes.ecommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = new Product();
        updateProductRequest(product, productRequest);
        Product savedProduct = productRepository.save(product);

        return mapToProductResponse(savedProduct);
    }


    public List<ProductResponse> getAllProducts() {
        List<Product> allProduct = productRepository.findByActiveTrue();
        return allProduct.stream()
                .map(product -> mapToProductResponse(product))
                .toList();
    }

    public Optional<ProductResponse> updateProduct(Long id, ProductRequest productRequest) {
        return productRepository.findById(id)
                .map(exitstingProduct -> {
                    updateProductRequest(exitstingProduct, productRequest);
                    Product savedProduct = productRepository.save(exitstingProduct);
                    return mapToProductResponse(savedProduct);
                });
    }

    public boolean deleteProduct(Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setActive(false);
                    productRepository.save(product);
                    return true;
                }).orElse(false);
    }

    public List<ProductResponse> searchProduct(String keyword) {
        return productRepository.searchProducts(keyword)
                .stream()
                .map(product -> mapToProductResponse(product))
                .toList();
    }

    private ProductResponse mapToProductResponse(Product savedProduct) {
        ProductResponse response = new ProductResponse();
        response.setId(savedProduct.getId());
        response.setName(savedProduct.getName());
        response.setActive(savedProduct.getActive());
        response.setDescription(savedProduct.getDescription());
        response.setPrice(savedProduct.getPrice());
        response.setStockQuantity(savedProduct.getStockQuantity());
        response.setCategory(savedProduct.getCategory());
        response.setImageUrl(savedProduct.getImageUrl());

        return response;
    }

    private void updateProductRequest(Product product, ProductRequest productRequest) {
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setCategory(productRequest.getCategory());
        product.setImageUrl(productRequest.getImageUrl());
    }

    public Optional<ProductResponse> getProductById(String id) {
        return productRepository.findByIdAndActiveTrue(Long.valueOf(id))
                .map(product -> mapToProductResponse(product));

    }
}
