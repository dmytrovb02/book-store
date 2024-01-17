package com.bookstore.service;

import com.bookstore.dto.cart.CartItemRequestDto;
import com.bookstore.dto.cart.CartItemResponseDto;
import com.bookstore.dto.cart.ShoppingCartResponseDto;

public interface ShoppingCartService {
    ShoppingCartResponseDto findCart(String email);

    ShoppingCartResponseDto addItemToCart(String email, CartItemRequestDto requestDto);

    CartItemResponseDto updateItem(Long id, CartItemRequestDto requestDto);

    void deleteItemById(Long id);
}
