package com.example.biblio.service;

import com.example.biblio.dto.UserDTO;
import com.example.biblio.model.ReaderTicket;
import com.example.biblio.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    User getUserByName(String name);

    User getUserByEmail(String mail);

    UserDTO getUser(String name);
    Boolean checkUserForExist(String name);

    Boolean ifUserSigned(Principal principal);

    User create(UserDTO dto);

    void putUser(User user, User userCurrent);

    void Save(User user);

    void Save(UserDTO dto);

    List<UserDTO> getAllUsers();
    void save (User user);

}
