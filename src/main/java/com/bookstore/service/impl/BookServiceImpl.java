package com.bookstore.service.impl;

import com.bookstore.dto.BookDto;
import com.bookstore.dto.BookRequestDto;
import com.bookstore.exception.BookNotFoundException;
import com.bookstore.exception.message.ExceptionMessage;
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
    public void create(BookRequestDto bookRequestDto) {
        Book book = bookMapper.mapToEntity(bookRequestDto);
        book.setStatus(BookStatus.ACTIVE);
        bookRepository.save(book);
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .filter(book -> !isDeleted(book))
                .map(bookMapper::mapToDto)
                .toList();
    }

    @Override
    public BookDto findById(Long id) {
        Book book = getBookById(id);
        if (isDeleted(book)) {
            throw new BookNotFoundException(
                    String.format(ExceptionMessage.BOOK_IS_DELETED.getMessage(), id));
        }
        return bookMapper.mapToDto(getBookById(id));
    }

    @Override
    public void update(Long id, BookRequestDto bookRequestDto) {
        Book book = getBookById(id);
        bookMapper.updateBookFromDto(bookRequestDto, book);
        bookRepository.save(book);
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
                        String.format(ExceptionMessage.BOOK_NOT_FOUND.getMessage(), id)));
    }

    private Boolean isDeleted(Book book) {
        return book.getStatus() == BookStatus.DELETED;
    }
}
