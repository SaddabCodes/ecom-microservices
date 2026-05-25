package com.sadcodes.ecomapplication.repository;

import com.sadcodes.ecomapplication.model.CartItem;
import com.sadcodes.ecomapplication.model.Product;
import com.sadcodes.ecomapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserAndProduct(User user, Product product);
}
