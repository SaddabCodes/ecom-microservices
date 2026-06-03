package com.sadcodes.ecommerce.user.repository;

import com.sadcodes.ecommerce.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
