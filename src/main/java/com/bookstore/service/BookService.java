package com.bookstore.service;

import com.bookstore.dto.BookRequestDto;
import com.bookstore.dto.BookResponceDto;
import java.util.List;

public interface BookService {

    BookResponceDto save(BookRequestDto bookRequestDto);

    List<BookResponceDto> findAll();

    BookResponceDto findById(Long id);

    BookResponceDto update(Long id, BookRequestDto bookRequestDto);

    void delete(Long id);
}
