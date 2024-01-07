package com.bookstore.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessage {

    BOOK_NOT_FOUND("Failed to retrieve book with id: %s");

    private final String message;
}
