package com.bookstore.dto.oredr;

import com.bookstore.model.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NonNull;

@Data
public class UpdateStatusOrderDto {

    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private OrderStatus status;
}
