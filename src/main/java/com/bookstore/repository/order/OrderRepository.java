package com.bookstore.repository.order;

import com.bookstore.model.Order;
import com.bookstore.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);
}
