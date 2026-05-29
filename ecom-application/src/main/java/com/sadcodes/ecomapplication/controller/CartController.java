package com.sadcodes.ecomapplication.controller;

import com.sadcodes.ecomapplication.dto.CartItemRequest;
import com.sadcodes.ecomapplication.model.CartItem;
import com.sadcodes.ecomapplication.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;


    @PostMapping
    public ResponseEntity<String> addToCart(@RequestHeader("X-USER-ID") String userId, @RequestBody CartItemRequest request) {

        if (!cartService.addToCart(userId, request)) {
            return ResponseEntity.badRequest().body("Product Out of Stock or User not found or Product not found");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/item/{productId}")
    public ResponseEntity<Void> removeCart(@RequestHeader("X-USER-ID") String userId, @PathVariable Long productId) {
        boolean deleted = cartService.deleteItemFromCart(userId, productId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();

    }

    @GetMapping()
    public ResponseEntity<List<CartItem>>getCart(@RequestHeader("X-USER-ID") String userId){
        return new ResponseEntity<>(cartService.getCart(userId),HttpStatus.OK);
    }
}
