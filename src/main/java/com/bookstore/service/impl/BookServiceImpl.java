package com.bookstore.service.impl;

import com.bookstore.dto.BookRequestDto;
import com.bookstore.dto.BookResponseDto;
import com.bookstore.dto.BookSearchParametersDto;
import com.bookstore.exception.EntityNotFoundException;
import com.bookstore.mapper.BookMapper;
import com.bookstore.model.Book;
import com.bookstore.repository.book.BookRepository;
import com.bookstore.repository.book.BookSpecificationBuilder;
import com.bookstore.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;

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
        return bookRepository.findById(id)
                .map(bookMapper::mapToDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Book with id: %s not found", id)));

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

    @Override
    public List<BookResponseDto> search(BookSearchParametersDto params) {
        Specification<Book> bookSpecification = bookSpecificationBuilder.build(params);
        return bookRepository.findAll(bookSpecification).stream()
                .map(bookMapper::mapToDto)
                .toList();
    }

    private Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Book with id: %s not found", id)));
    }
}
