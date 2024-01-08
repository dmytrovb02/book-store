package com.bookstore.mapper;

import com.bookstore.config.MapperConfig;
import com.bookstore.dto.BookRequestDto;
import com.bookstore.dto.BookResponceDto;
import com.bookstore.model.Book;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(imports = MapperConfig.class, componentModel = "spring")
public interface BookMapper {

    BookResponceDto mapToDto(Book book);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBookFromDto(BookRequestDto dto, @MappingTarget Book entity);

    Book mapToEntity(BookRequestDto bookRequestDto);
}
