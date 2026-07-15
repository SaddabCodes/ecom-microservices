package com.sadcodes.ecommerce.product.repository;

import com.sadcodes.ecommerce.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByActiveTrue();

    @Query("SELECT p FROM Product p WHERE p.active = true AND p.stockQuantity > 0 AND LOWER(p.name) LIKE LOWER(concat('%', :keyword, '%'))")
    List<Product> searchProducts(@Param("keyword") String keyword);

    Optional<Product> findByIdAndActiveTrue(Long id);

}
