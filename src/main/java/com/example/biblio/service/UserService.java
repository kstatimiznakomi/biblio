package com.example.biblio.service;

import com.example.biblio.dto.UserDTO;
import com.example.biblio.model.ReaderTicket;
import com.example.biblio.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getUserByName(String name);
    UserDTO getUser(String name);
    Boolean checkUserForExist(String name);
    void Save(UserDTO dto);
    void block(User user);
    void unblock(User user);
}
