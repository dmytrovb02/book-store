package com.bookstore.dto.book;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    @Size(min = 5)
    private String isbn;

    @DecimalMin(value = "0.0")
    @NotNull
    private BigDecimal price;

    @NotBlank
    @Size(min = 20, max = 200)
    private String description;

    private String coverImage;
}
