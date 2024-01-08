package com.bookstore.service;

import com.bookstore.dto.BookRequestDto;
import com.bookstore.dto.BookResponseDto;
import java.util.List;

public interface BookService {

    BookResponseDto create(BookRequestDto bookRequestDto);

    List<BookResponseDto> findAll();

    BookResponseDto findById(Long id);

    BookResponseDto update(Long id, BookRequestDto bookRequestDto);

    void delete(Long id);
}
