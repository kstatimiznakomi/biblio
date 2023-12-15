package com.example.biblio.service;

import com.example.biblio.dto.UserDTO;
import com.example.biblio.model.ReaderTicket;
import com.example.biblio.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Principal;
@Service
public interface UserService extends UserDetailsService {
    User getUserByName(String name);
    UserDTO getUser(String name);
    Boolean checkUserForExist(String name);

    Boolean ifUserSigned(Principal principal);

    void Save(UserDTO dto);
}
