package com.bookstore.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessage {

    BOOK_NOT_FOUND("Failed to retrieve book with id: %s"),
    BOOK_IS_DELETED("Book with id: %s was deleted");

    private final String message;
}
