package com.bookstore.service;

import com.bookstore.dto.book.BookDtoWithoutCategoryIds;
import com.bookstore.dto.book.BookRequestDto;
import com.bookstore.dto.book.BookResponseDto;
import com.bookstore.dto.book.BookSearchParametersDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {

    BookResponseDto create(BookRequestDto bookRequestDto);

    List<BookResponseDto> findAll(Pageable pageable);

    BookResponseDto findById(Long id);

    List<BookDtoWithoutCategoryIds> findAllByCategoryId(Long categoryId);

    BookResponseDto update(Long id, BookRequestDto bookRequestDto);

    void delete(Long id);

    List<BookResponseDto> search(BookSearchParametersDto bookSearchParametersDto);
}
