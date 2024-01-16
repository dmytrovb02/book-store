package com.bookstore.service.impl;

import com.bookstore.dto.shoppingcart.CartItemRequestDto;
import com.bookstore.dto.shoppingcart.ShoppingCartResponseDto;
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
    public ShoppingCartResponseDto addItem(String email, CartItemRequestDto requestDto) {
        User user = userService.getUserByEmail(email);
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser(user);
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
            shoppingCartRepository.save(shoppingCart);
        }
        CartItem cartItem = cartItemMapper.toModel(requestDto);
        cartItem.setShoppingCart(shoppingCart);

        Book book = bookService.getBookById(requestDto.getBookId());
        cartItem.setBook(book);

        cartItemRepository.save(cartItem);
        ShoppingCart shoppingCartResponse = shoppingCartRepository.findShoppingCartByUser(user);
        return shoppingCartMapper.toDto(shoppingCartResponse);
    }

    @Override
    public void updateItem(Long id, CartItemRequestDto requestDto) {
        CartItem cartItem = cartItemRepository.findCartItemById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can`t find cart item by id " + id));
        cartItemMapper.updateCartItemFromDto(requestDto, cartItem);
        cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteById(Long id) {
        cartItemRepository.deleteById(id);
    }
}
