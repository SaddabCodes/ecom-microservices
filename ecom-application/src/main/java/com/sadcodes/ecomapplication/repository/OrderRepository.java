package com.sadcodes.ecomapplication.repository;

import com.sadcodes.ecomapplication.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
