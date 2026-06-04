package com.sadcodes.ecommerce.user.repository;

import com.sadcodes.ecommerce.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
