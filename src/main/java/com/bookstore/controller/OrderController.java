package com.bookstore.controller;

import com.bookstore.dto.oredr.OrderItemResponseDto;
import com.bookstore.dto.oredr.OrderRequestDto;
import com.bookstore.dto.oredr.OrderResponseDto;
import com.bookstore.dto.oredr.UpdateStatusOrderDto;
import com.bookstore.model.User;
import com.bookstore.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
@Tag(name = "Order", description = "Endpoints for managing orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('USER')")
    @Operation(summary = "Create a new order")
    public OrderResponseDto createOrder(Authentication authentication,
                                        @RequestBody @Valid OrderRequestDto requestDto) {
        User user = (User) authentication.getPrincipal();
        return orderService.create(user, requestDto);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER')")
    @Operation(summary = "Get all orders")
    public List<OrderResponseDto> getAllOrders(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return orderService.getAllOrders(user);
    }

    @GetMapping("/{orderId}/items")
    @PreAuthorize("hasAnyAuthority('USER')")
    @Operation(summary = "Get all order items",
            description = "Get all order items by order id")
    public List<OrderItemResponseDto> getAllOrderItems(@PathVariable Long orderId) {
        return orderService.getAllOrderItems(orderId);
    }

    @GetMapping("/{orderId}/items/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    @Operation(summary = "Get a order item",
            description = "Get a order item by order id and order item id")
    public OrderItemResponseDto getOrderItem(@PathVariable Long orderId,
                                             @PathVariable Long id) {
        return orderService.getOrderItem(orderId, id);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Update status",
            description = "Update order status by order id")
    public OrderResponseDto getOrderItem(@PathVariable Long id,
                                         @RequestBody UpdateStatusOrderDto orderDto) {
        return orderService.updateOrderStatus(id, orderDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    @Operation(summary = "Delete order",
            description = "Delete order by order id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
    }
}
