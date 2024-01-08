package com.bookstore.controller;

import com.bookstore.dto.BookRequestDto;
import com.bookstore.dto.BookResponseDto;
import com.bookstore.service.impl.BookServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookServiceImpl bookService;

    @GetMapping
    public List<BookResponseDto> getAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookResponseDto getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponseDto createBook(@RequestBody BookRequestDto bookRequestDto) {
        return bookService.save(bookRequestDto);
    }
}
