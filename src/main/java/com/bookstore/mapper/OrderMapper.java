package com.bookstore.mapper;

import com.bookstore.config.MapperConfig;
import com.bookstore.dto.oredr.OrderItemResponseDto;
import com.bookstore.dto.oredr.OrderResponseDto;
import com.bookstore.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(imports = MapperConfig.class, uses = OrderItemResponseDto.class, componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "orderItems", source = "orderItems")
    OrderResponseDto toDto(Order order);
}
