package com.bookstore.repository.cart;

import com.bookstore.model.ShoppingCart;
import com.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findShoppingCartByUser(User user);
}
