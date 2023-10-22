package com.example.biblio.Controller;

import com.example.biblio.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @GetMapping("")
    public String index(Model model){
        model.addAttribute("user", new UserDTO());
        return "profile";
    }
    @PatchMapping ("/patch")
    public String profile(){
        return "profile";
    }
}
