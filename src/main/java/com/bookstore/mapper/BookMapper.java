package com.bookstore.mapper;

import com.bookstore.config.MapperConfig;
import com.bookstore.dto.BookDto;
import com.bookstore.dto.BookRequestDto;
import com.bookstore.model.Book;
import org.mapstruct.Mapper;

@Mapper(imports = MapperConfig.class, componentModel = "spring")
public interface BookMapper {

    BookDto mapToDto(Book book);

    Book mapToEntity(BookRequestDto bookRequestDto);
}
