package com.bookstore.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
public class UserLoginRequestDto {
    @NotBlank
    @Length(min = 8, max = 20)
    private String email;

    @NotBlank
    @Length(min = 8, max = 20)
    private String password;
}
