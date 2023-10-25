package com.example.biblio.Controller;

import com.example.biblio.dto.UserDTO;
import com.example.biblio.model.User;
import com.example.biblio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping({"/register", "/registration"})
@AllArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @GetMapping("")
    public String index(Model model, Principal principal){
        if (principal != null) return "redirect:/catalog";
        model.addAttribute("user", new UserDTO());
        return "registration-page";
    }
    @GetMapping("/new")
    public String check(){
        return "redirect:/catalog";
    }
    @PostMapping("/new")
    public String registrate(UserDTO dto){
        if (!userService.checkUserForExist(dto.getName())) userService.Save(dto);
        else return "redirect:/catalog";
        return "redirect:/catalog";
    }
}
