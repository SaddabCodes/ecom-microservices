package com.sadcodes.ecommerce.order.repository;

import com.sadcodes.ecommerce.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
