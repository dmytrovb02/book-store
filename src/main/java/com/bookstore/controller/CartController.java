package com.bookstore.controller;

import com.bookstore.dto.cart.CartItemRequestDto;
import com.bookstore.dto.cart.CartItemResponseDto;
import com.bookstore.dto.cart.ShoppingCartResponseDto;
import com.bookstore.model.User;
import com.bookstore.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Cart controller", description = "Endpoints for managing shopping cart")
public class CartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER')")
    @Operation(summary = "Get a shopping cart",
            description = "Get a user's shopping cart")
    public ShoppingCartResponseDto findCart(
            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.findCart(user.getEmail());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('USER')")
    @Operation(summary = "Add an item",
            description = "Add a new item to shopping cart")
    public ShoppingCartResponseDto addItem(
            @RequestBody CartItemRequestDto cartItemRequestDto,
            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.addItem(user.getEmail(), cartItemRequestDto);
    }

    @PutMapping("cart-items/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    @Operation(summary = "Update some item",
            description = "Update some item by id")
    public CartItemResponseDto updateItem(
            @PathVariable Long id,
            @RequestBody CartItemRequestDto cartItemRequestDto) {
        return shoppingCartService.updateItem(id, cartItemRequestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("cart-items/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    @Operation(summary = "Delete some item",
            description = "Delete some item by id")
    public void deleteById(@PathVariable Long id) {
        shoppingCartService.deleteById(id);
    }
}
