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
    @Size(min = 2, max = 30,  message = "Минимальная длина фамилии - 2")
    private String lastname;

    @NotBlank(message = "Имя требуется")
    @Size(min = 2, max = 30,  message = "Минимальная длина имени - 2")
    private String name;

    @NotBlank(message = "Отчество требуется")
    @Size(min = 2, max = 30,  message = "Минимальная длина отчества - 2")
    private String surname;

    @NotBlank(message = "Имя пользователя требуется")
    @Size(min = 2, max = 30,  message = "Минимальная длина имени пользователя - 2")
    private String username;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 4, max = 30,  message = "Длина пароля должна быть 4-30")
    private String password;

    @NotBlank(message = "Почта требуется")
    @Email(message = "Неверно введена почта")
    @Size(min = 2, max = 30,  message = "Минимальная длина почты - 2")
    private String email;

    @NotBlank(message = "Почта требуется")
    @Size(min = 11, max = 12,  message = "Длина номера телефона: 11-12")
    private String phone;
}
