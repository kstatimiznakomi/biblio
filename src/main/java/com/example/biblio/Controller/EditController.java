package com.example.biblio.Controller;

import com.example.biblio.dto.UserDTO;
import com.example.biblio.model.User;
import com.example.biblio.service.UserService;
import com.example.biblio.service.ValidationComponent;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@AllArgsConstructor
public class EditController {
    private final UserService userService;
    @PutMapping(value = "/profile/put", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> checkUser(@Valid @RequestBody ValidationComponent validationComponent, Principal principal) throws Exception {
        System.out.println(validationComponent);
        //@Valid
        //юзера в json и все ошибки
        //ассоциативный массив json, напр.
        //form error
        //healthcheck
        User userCurrent = userService.getUserByName(principal.getName());

        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(validationComponent, dto);
        User user1 = userService.createWoutPass(dto);

        //userService.putUserWoutPass(user1, userCurrent);

        return new ResponseEntity<>("Данные успешно изменены!", HttpStatus.OK);
    }
}
