package com.example.biblio.Controller;

import com.example.biblio.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @GetMapping("")
    public String index(Model model, Principal principal){
        if (principal == null) return "redirect:/login";
        model.addAttribute("user", principal);
        return "profile";
    }

    @GetMapping("/edit")
    public String edit(Model model, Principal principal){
        if (principal == null) return "redirect:/login";
        return "profile-edit";
    }

    @PatchMapping ("/edit/patch")
    public String profile(Model model){
        model.addAttribute("user", new UserDTO());
        return "profile";
    }
}
