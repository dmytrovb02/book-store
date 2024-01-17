package com.bookstore.service.impl;

import com.bookstore.dto.oredr.OrderItemResponseDto;
import com.bookstore.dto.oredr.OrderRequestDto;
import com.bookstore.dto.oredr.OrderResponseDto;
import com.bookstore.dto.oredr.UpdateStatusOrderDto;
import com.bookstore.exception.EntityNotFoundException;
import com.bookstore.mapper.OrderItemMapper;
import com.bookstore.mapper.OrderMapper;
import com.bookstore.model.Order;
import com.bookstore.model.OrderItem;
import com.bookstore.model.OrderStatus;
import com.bookstore.model.ShoppingCart;
import com.bookstore.model.User;
import com.bookstore.repository.cart.ShoppingCartRepository;
import com.bookstore.repository.order.OrderItemRepository;
import com.bookstore.repository.order.OrderRepository;
import com.bookstore.service.OrderService;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    @Transactional
    public OrderResponseDto create(User user, OrderRequestDto requestDto) {
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setShippingAddress(requestDto.getShippingAddress());
        orderRepository.save(order);

        order.setOrderItems(getOrderItems(user, order));
        order.setTotalPrice(order.getOrderItems()
                .stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        orderRepository.save(order);

        return orderMapper.toDto(order);
    }

    private Set<OrderItem> getOrderItems(User user, Order order) {
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser(user);

        Set<OrderItem> orderItems = shoppingCart.getCartItems().stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setBook(cartItem.getBook());
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setPrice(cartItem.getBook().getPrice()
                            .multiply(BigDecimal.valueOf(cartItem.getQuantity())));
                    orderItem.setOrder(order);
                    return orderItem;
                })
                .collect(Collectors.toSet());

        orderItemRepository.saveAll(orderItems);
        return orderItems;
    }

    @Override
    public List<OrderResponseDto> getAllOrders(User user) {
        return orderRepository.findAllByUser(user)
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public List<OrderItemResponseDto> getAllOrderItems(Long orderId) {
        return orderItemRepository.findOrderItemsByOrder(getOrder(orderId))
                .stream().map(orderItemMapper::toDto)
                .toList();
    }

    @Override
    public OrderItemResponseDto getOrderItem(Long orderId, Long id) {
        return getOrder(orderId).getOrderItems()
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(orderItemMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find order item by id: " + id));
    }

    @Override
    public OrderResponseDto updateOrderStatus(Long id, UpdateStatusOrderDto orderDto) {
        Order order = getOrder(id);
        order.setStatus(orderDto.getStatus());
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.delete(getOrder(id));
    }

    private Order getOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Can't find order by id: " + id));
    }
}
