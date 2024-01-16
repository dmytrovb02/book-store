package com.bookstore.dto.shoppingcart;

import lombok.Data;

@Data
public class CartItemRequestDto {
    private Long bookId;
    private int quantity;
}
