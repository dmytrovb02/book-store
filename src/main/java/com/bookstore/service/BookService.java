package com.bookstore.service;

import com.bookstore.dto.BookDto;
import com.bookstore.dto.BookRequestDto;
import java.util.List;

public interface BookService {

    void create(BookRequestDto bookRequestDto);

    List<BookDto> findAll();

    BookDto findById(Long id);

    void update(Long id, BookRequestDto bookRequestDto);

    void delete(Long id);

}
