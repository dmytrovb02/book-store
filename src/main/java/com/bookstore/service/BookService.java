package com.bookstore.service;

import com.bookstore.dto.BookDto;
import com.bookstore.dto.BookRequestDto;
import java.util.List;

public interface BookService {

    void save(BookRequestDto bookRequestDto);

    List<BookDto> findAll();

    BookDto findById(Long id);
}
