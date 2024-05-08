package com.example.biblio.Controller;

import com.example.biblio.dto.UserDTO;
import com.example.biblio.model.User;
import com.example.biblio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
@Lazy
public class RegistrationController {
    private final UserService userService;

    @GetMapping("")
    public String index(Model model, Principal principal){
        if (principal != null) return "redirect:/catalog";
        model.addAttribute("user", new UserDTO());
        return "registration-page";
    }

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> registrate(@RequestBody User user){
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        User user1 = userService.create(dto);
        if (userService.getUserByEmail(user1.getEmail()) != null) {
            throw new RuntimeException("такой пользователь уже существует");
        }
        else userService.Save(user1);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
