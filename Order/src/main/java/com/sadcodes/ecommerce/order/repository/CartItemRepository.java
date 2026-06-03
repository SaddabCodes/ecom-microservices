package com.sadcodes.ecommerce.order.repository;


import com.sadcodes.ecommerce.order.model.CartItem;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserAndProduct(User user, Product product);

    @Modifying
    @Query("DELETE FROM CartItem c WHERE c.user = :user AND c.product = :product")
    void deleteByUserAndProduct(@Param("user") User user, @Param("product") Product product);

    List<CartItem> findByUser(User user);

    void deleteByUser(User user);
}
