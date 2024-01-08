package com.bookstore.service.impl;

import com.bookstore.dto.BookRequestDto;
import com.bookstore.dto.BookResponceDto;
import com.bookstore.exception.BookNotFoundException;
import com.bookstore.mapper.BookMapper;
import com.bookstore.model.Book;
import com.bookstore.model.BookStatus;
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
    public BookResponceDto save(BookRequestDto bookRequestDto) {
        Book book = bookMapper.mapToEntity(bookRequestDto);
        book.setStatus(BookStatus.ACTIVE);
        bookRepository.save(book);
        return bookMapper.mapToDto(book);
    }

    @Override
    public List<BookResponceDto> findAll() {
        return bookRepository.findAll().stream()
                .filter(book -> !isDeleted(book))
                .map(bookMapper::mapToDto)
                .toList();
    }

    @Override
    public BookResponceDto findById(Long id) {
        Book book = getBookById(id);
        if (isDeleted(book)) {
            throw new BookNotFoundException(
                    String.format("Book with id: %s was deleted", id));
        }
        return bookMapper.mapToDto(getBookById(id));
    }

    @Override
    public BookResponceDto update(Long id, BookRequestDto bookRequestDto) {
        Book book = getBookById(id);
        bookMapper.updateBookFromDto(bookRequestDto, book);
        bookRepository.save(book);
        return bookMapper.mapToDto(book);
    }

    @Override
    public void delete(Long id) {
        Book book = getBookById(id);
        book.setStatus(BookStatus.DELETED);
        bookRepository.save(book);
    }

    private Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new BookNotFoundException(
                        String.format("Book with id: %s not found", id)));
    }

    private Boolean isDeleted(Book book) {
        return book.getStatus() == BookStatus.DELETED;
    }
}
