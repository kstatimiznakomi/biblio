package com.example.biblio.service;

import com.example.biblio.dto.UserDTO;
import com.example.biblio.model.ReaderTicket;
import com.example.biblio.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User GetUserByUserName(String userName);

    void Save(UserDTO dto);
    void Block(User user);

}
