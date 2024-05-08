package com.example.biblio.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationComponent {
    @NotBlank(message = "Фамилия требуется")
    @Size(min = 2,  message = "Минимальная длина 2")
    private String lastname;

    @NotBlank(message = "Имя требуется")
    @Size(min = 2,  message = "Минимальная длина 2")
    private String name;

    @NotBlank(message = "Отчество требуется")
    @Size(min = 2,  message = "Минимальная длина 2")
    private String surname;

    @NotBlank(message = "Имя пользователя требуется")
    @Size(min = 2,  message = "Минимальная длина 2")
    private String username;

    private String password;
    @NotBlank(message = "Почта требуется")
    @Size(min = 2,  message = "Минимальная длина 2")
    @Email(message = "Неверно введена почта")
    private String email;
    private String phone;
}
