package com.bookstore.service.impl;

import com.bookstore.dto.cart.CartItemRequestDto;
import com.bookstore.dto.cart.CartItemResponseDto;
import com.bookstore.dto.cart.ShoppingCartResponseDto;
import com.bookstore.exception.EntityNotFoundException;
import com.bookstore.mapper.CartItemMapper;
import com.bookstore.mapper.ShoppingCartMapper;
import com.bookstore.model.Book;
import com.bookstore.model.CartItem;
import com.bookstore.model.ShoppingCart;
import com.bookstore.model.User;
import com.bookstore.repository.cart.CartItemRepository;
import com.bookstore.repository.cart.ShoppingCartRepository;
import com.bookstore.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
    private final ShoppingCartMapper shoppingCartMapper;
    private final UserServiceImpl userService;
    private final BookServiceImpl bookService;

    @Override
    public ShoppingCartResponseDto findCart(String email) {
        User user = userService.getUserByEmail(email);
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser(user);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartResponseDto addItemToCart(String email, CartItemRequestDto requestDto) {
        User user = userService.getUserByEmail(email);
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser(user);

        CartItem cartItem = cartItemMapper.toModel(requestDto);
        cartItem.setShoppingCart(shoppingCart);

        Book book = bookService.getBookById(requestDto.getBookId());
        cartItem.setBook(book);

        cartItemRepository.save(cartItem);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public CartItemResponseDto updateItem(Long id, CartItemRequestDto requestDto) {
        CartItem cartItem = cartItemRepository.findCartItemById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can`t find cart item by id " + id));
        cartItemMapper.updateCartItemFromDto(requestDto, cartItem);
        cartItemRepository.save(cartItem);
        return cartItemMapper.toDto(cartItem);
    }

    @Override
    public void deleteItemById(Long id) {
        cartItemRepository.deleteById(id);
    }
}
