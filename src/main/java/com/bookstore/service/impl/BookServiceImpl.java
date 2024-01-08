package com.bookstore.service.impl;

import com.bookstore.dto.BookRequestDto;
import com.bookstore.dto.BookResponseDto;
import com.bookstore.exception.EntityNotFoundException;
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
    public BookResponseDto create(BookRequestDto bookRequestDto) {
        Book book = bookMapper.mapToEntity(bookRequestDto);
        bookRepository.save(book);
        return bookMapper.mapToDto(book);
    }

    @Override
    public List<BookResponseDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::mapToDto)
                .toList();
    }

    @Override
    public BookResponseDto findById(Long id) {
        return bookMapper.mapToDto(getBookById(id));
    }

    @Override
    public BookResponseDto update(Long id, BookRequestDto bookRequestDto) {
        Book book = getBookById(id);
        bookMapper.updateBookFromDto(bookRequestDto, book);
        bookRepository.save(book);
        return bookMapper.mapToDto(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    private Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Book with id: %s not found", id)));
    }
}
