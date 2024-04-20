package com.example.biblio.Controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class LoginController {
    @GetMapping("/login")
    public String login(Principal principal){
        if(principal != null){
            return "redirect:/catalog";
        }
        return "login";
    }
}
