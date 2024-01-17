package com.bookstore.mapper;

import com.bookstore.config.MapperConfig;
import com.bookstore.dto.oredr.OrderItemResponseDto;
import com.bookstore.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(imports = MapperConfig.class, componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target = "bookId", source = "book.id")
    OrderItemResponseDto toDto(OrderItem orderItem);
}
