package com.bookstore.controller;

import com.bookstore.dto.shoppingcart.CartItemRequestDto;
import com.bookstore.dto.shoppingcart.ShoppingCartResponseDto;
import com.bookstore.model.User;
import com.bookstore.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER')")
    public ShoppingCartResponseDto findCart(
            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.findCart(user.getEmail());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('USER')")
    public ShoppingCartResponseDto addItem(
            @RequestBody CartItemRequestDto cartItemRequestDto,
            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.addItem(user.getEmail(), cartItemRequestDto);
    }

    @PutMapping("cart-items/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ShoppingCartResponseDto updateItem(
            @PathVariable Long id,
            @RequestBody CartItemRequestDto cartItemRequestDto,
            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.updateItem(id, user.getEmail(), cartItemRequestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("cart-items/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public void deleteById(@PathVariable Long id) {
        shoppingCartService.deleteById(id);
    }
}
