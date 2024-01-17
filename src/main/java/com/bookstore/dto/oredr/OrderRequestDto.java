package com.bookstore.dto.oredr;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

@Data
public class OrderRequestDto {

    @NonNull
    private String shippingAddress;

    public OrderRequestDto(@JsonProperty("shippingAddress") String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
