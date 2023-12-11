package com.example.biblio.Controller;

import com.example.biblio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class MainController {
    private final UserService userService;
    @GetMapping({"", "/"})
    public String index(Principal principal, Model model){
        if(principal != null) model.addAttribute("user", userService.getUserByName(principal.getName()));
        return "main";
    }
}
