package com.example.biblio.dto;

import com.example.biblio.model.UserRole;
import com.example.biblio.model.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String lastname;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String verifyPassword;
    private String email;
    private String address;
    private UserStatus status;
    private UserRole role;
    private String phone;
}
