package com.example.biblio.Controller;

import com.example.biblio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"", "/"})
@AllArgsConstructor
public class MainController {
    private final UserService userService;
    @GetMapping("")
    public String Index(){

        return "index";
    }
}
