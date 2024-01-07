package com.bookstore.service.impl;

import com.bookstore.dto.BookDto;
import com.bookstore.dto.BookRequestDto;
import com.bookstore.exception.EntityNotFoundException;
import com.bookstore.exception.message.ExceptionMessage;
import com.bookstore.mapper.BookMapper;
import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public void save(BookRequestDto bookRequestDto) {
        bookRepository.save(bookMapper.mapToEntity(bookRequestDto));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::mapToDto)
                .toList();
    }

    @Override
    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format(ExceptionMessage.BOOK_NOT_FOUND.getMessage(), id)));
        return bookMapper.mapToDto(book);
    }
}
