package com.example.demo.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginRequest {
    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String confirmPassword;
}
