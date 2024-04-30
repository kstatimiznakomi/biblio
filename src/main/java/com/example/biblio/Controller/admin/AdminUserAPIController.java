package com.example.biblio.Controller.admin;

import com.example.biblio.model.UserRole;
import com.example.biblio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.biblio.dto.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
public class AdminUserAPIController {
    @Lazy
    private final UserService userService;

    @GetMapping("/admin/users/api")
    public List<UserDTO> getAllUsers(Model model, Principal principal) {
        if (principal != null &&
                userService.getUserByName(principal.getName()).getRole().equals(UserRole.Администратор)){
            return userService.getAllUsers();
        }
        return null;
    }
}
