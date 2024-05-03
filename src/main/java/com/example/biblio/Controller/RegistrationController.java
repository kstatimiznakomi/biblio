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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
@Lazy
public class RegistrationController {
    private final UserService userService;
    private final BCryptPasswordEncoder encoder;

    @GetMapping("")
    public String index(Model model, Principal principal){
        if (principal != null) return "redirect:/catalog";
        model.addAttribute("user", new UserDTO());
        return "registration-page";
    }

    @PutMapping(value = "/put", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> checkUser(@RequestBody User user, Principal principal) {
        User userCurrent = userService.getUserByName(principal.getName());

        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        User user1 = userService.create(dto);

        userService.putUser(user1, userCurrent);

        return new ResponseEntity<>(HttpStatus.OK);
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
