package com.bookstore.mapper;

import com.bookstore.config.MapperConfig;
import com.bookstore.dto.shoppingcart.CartItemRequestDto;
import com.bookstore.dto.shoppingcart.CartItemResponseDto;
import com.bookstore.model.CartItem;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(imports = MapperConfig.class, componentModel = "spring")
public interface CartItemMapper {

    @Mapping(target = "bookId", source = "book.id")
    @Mapping(target = "bookTitle", source = "book.title")
    CartItemResponseDto toDto(CartItem cartItem);

    CartItem toModel(CartItemRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCartItemFromDto(CartItemRequestDto dto, @MappingTarget CartItem entity);
}
