package com.sadcodes.ecommerce.order.repository;


import com.sadcodes.ecommerce.order.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserIdAndProductId(String userId, String productId);

    void deleteByUserIdAndProductId(@Param("user") String userId, @Param("product") String productId);

    List<CartItem> findByUserId(String userId);

    void deleteByUserId(String userId);
}
