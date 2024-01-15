package com.bookstore.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryRequestDto {

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    private String description;
}
