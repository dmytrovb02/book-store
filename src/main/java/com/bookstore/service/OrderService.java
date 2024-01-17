package com.bookstore.service;

import com.bookstore.dto.oredr.OrderItemResponseDto;
import com.bookstore.dto.oredr.OrderRequestDto;
import com.bookstore.dto.oredr.OrderResponseDto;
import com.bookstore.dto.oredr.UpdateStatusOrderDto;
import com.bookstore.model.User;
import java.util.List;

public interface OrderService {
    OrderResponseDto create(User user, OrderRequestDto requestDto);

    List<OrderResponseDto> getAllOrders(User user);

    List<OrderItemResponseDto> getAllOrderItems(Long orderId);

    OrderItemResponseDto getOrderItem(Long orderId, Long id);

    OrderResponseDto updateOrderStatus(Long id, UpdateStatusOrderDto orderDto);

    void deleteOrderById(Long id);
}
