package com.sadcodes.ecommerce.order.dto;

import lombok.Data;

@Data
public class CartItemRequest {

    private String productId;
    private Integer quantity;
}
