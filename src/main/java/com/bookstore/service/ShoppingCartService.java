package com.bookstore.service;

import com.bookstore.dto.shoppingcart.CartItemRequestDto;
import com.bookstore.dto.shoppingcart.ShoppingCartResponseDto;

public interface ShoppingCartService {
    ShoppingCartResponseDto findCart(String email);

    ShoppingCartResponseDto addItem(String email, CartItemRequestDto requestDto);

    ShoppingCartResponseDto updateItem(Long id, String email, CartItemRequestDto requestDto);

    void deleteById(Long id);
}
