package com.example.biblio.Controller.admin;

import com.example.biblio.model.User;
import com.example.biblio.model.UserRole;
import com.example.biblio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin/users")
@AllArgsConstructor
public class AdminUserController {
    private final UserService userService;
    @GetMapping("")
    public String users(Model model, Principal principal) {
        if (principal != null &&
                userService.getUserByName(principal.getName()).getRole().equals(UserRole.Администратор)){
            model.addAttribute("users", userService.getAllUsers());
            return "admin/users";
        }
        return "redirect:/login";
    }

    @PostMapping("/{userId}/edit")
    public String saveUserEdit(@ModelAttribute User user, @PathVariable Long userId,
                               Principal principal) {
        if (principal != null &&
                userService.getUserByName(principal.getName()).getRole().equals(UserRole.Администратор)){
            user.setId(userId);
            userService.save(user);
            return "redirect:/admin/users";
        }
        return "redirect:/login";
    }
}
