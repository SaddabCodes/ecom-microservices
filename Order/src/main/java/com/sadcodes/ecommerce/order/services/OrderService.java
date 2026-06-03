package com.sadcodes.ecommerce.order.services;


import com.sadcodes.ecommerce.order.dto.OrderItemDto;
import com.sadcodes.ecommerce.order.dto.OrderResponse;
import com.sadcodes.ecommerce.order.model.CartItem;
import com.sadcodes.ecommerce.order.model.Order;
import com.sadcodes.ecommerce.order.model.OrderItem;
import com.sadcodes.ecommerce.order.model.OrderStatus;
import com.sadcodes.ecommerce.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;

    public Optional<OrderResponse> createOrder(String userId) {
        // Validate for cart items
        List<CartItem> cartItems = cartService.getCart(userId);
        if (cartItems.isEmpty()) {
            return Optional.empty();
        }

        // Validate for user

//        Optional<User> userOptional = userRepository.findById(Long.valueOf(userId));
//        if (userOptional.isEmpty()) {
//            return Optional.empty();
//        }
//        User user = userOptional.get();

        // Calculate total price
        BigDecimal totalPrice = cartItems.stream()
                .map(cartItem -> cartItem.getPrice())
                .reduce(BigDecimal.ZERO, (bigDecimal, augend) -> bigDecimal.add(augend));

        // Create Oder
        Order order = new Order();
        order.setUserId(userId);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setTotalAmount(totalPrice);

        List<OrderItem> orderItems = cartItems.stream()
                .map(item -> new OrderItem(
                        null, item.getProductId(), item.getQuantity(),
                        item.getPrice(), order
                )).toList();
        order.setItems(orderItems);
        Order saveOrder = orderRepository.save(order);

        // Clear the cart
        cartService.clearCart(userId);

        return Optional.of(mapToOrderResponse(saveOrder));
    }

    private OrderResponse mapToOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(), order.getTotalAmount(), order.getStatus(),
                order.getItems().stream()
                        .map(orderItem -> new OrderItemDto(
                                orderItem.getId(),
                                orderItem.getProductId(),
                                orderItem.getQuantity(),
                                orderItem.getPrice(),
                                orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity()))
                        ))
                        .toList(), order.getCreatedAt()

        );
    }
}
