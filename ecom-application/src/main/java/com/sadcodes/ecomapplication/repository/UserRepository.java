package com.sadcodes.ecomapplication.repository;

import com.sadcodes.ecomapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long> {
}
