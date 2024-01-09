package com.bookstore.service;

import com.bookstore.dto.BookRequestDto;
import com.bookstore.dto.BookResponseDto;
import com.bookstore.dto.BookSearchParametersDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {

    BookResponseDto create(BookRequestDto bookRequestDto);

    List<BookResponseDto> findAll(Pageable pageable);

    BookResponseDto findById(Long id);

    BookResponseDto update(Long id, BookRequestDto bookRequestDto);

    void delete(Long id);

    List<BookResponseDto> search(BookSearchParametersDto bookSearchParametersDto);
}
