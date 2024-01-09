package com.bookstore.controller;

import com.bookstore.dto.BookRequestDto;
import com.bookstore.dto.BookResponseDto;
import com.bookstore.service.impl.BookServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Tag(name = "Book", description = "Endpoints for managing books")
public class BookController {

    private final BookServiceImpl bookService;

    @GetMapping
    @Operation(summary = "Get all books", description = "Get a list of all available books")
    public List<BookResponseDto> getAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a book by id", description = "Get a book by id")
    public BookResponseDto getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a book", description = "Create a new book")
    public ResponseEntity<BookResponseDto> createBook(@RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto createdBook = bookService.create(bookRequestDto);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a book by id", description = "Update a book by id")
    public BookResponseDto updateBook(@PathVariable Long id,
                           @RequestBody BookRequestDto bookRequestDto) {
        return bookService.update(id, bookRequestDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book by id", description = "Delete a book by id")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }
}
