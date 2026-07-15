package com.sadcodes.ecommerce.product.controller;

import com.sadcodes.ecommerce.product.dto.ProductRequest;
import com.sadcodes.ecommerce.product.dto.ProductResponse;
import com.sadcodes.ecommerce.product.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.createProduct(productRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(id, productRequest).map(product -> ResponseEntity.ok(product)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all-product")
    public ResponseEntity<List<ProductResponse>> getProduct() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable String  id) {
        return productService.getProductById(id)
                .map(productResponse -> ResponseEntity.ok(productResponse))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.deleteProduct(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProduct(@RequestParam String keyword) {
        return ResponseEntity.ok(productService.searchProduct(keyword));
    }
}
