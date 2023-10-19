package com.example.biblio.service;

import com.example.biblio.dao.UserDAO;
import com.example.biblio.dto.UserDTO;
import com.example.biblio.model.ReaderTicket;
import com.example.biblio.model.User;
import com.example.biblio.model.UserRole;
import com.example.biblio.model.UserStatus;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder encoder;
    private final UserDAO userRepository;
    private final ReaderTicketService readerTicketService;

    public UserServiceImpl(BCryptPasswordEncoder encoder, @Lazy UserDAO userRepository, @Lazy ReaderTicketService readerTicketService) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.readerTicketService = readerTicketService;
    }

    @Override
    public User getUserByName(String name){
        return userRepository.findFirstByUsername(name);
    }

    @Override
    public Boolean checkUserForExist(String name){
        User user = userRepository.findFirstByUsername(name);
        return user != null;
    }

    @Override
    public void Save(UserDTO dto) {
        User user = User.builder()
                .lastname(dto.getLastname())
                .name(dto.getName())
                .surname(dto.getSurname())
                .username(dto.getUsername())
                .password(encoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .role(UserRole.Читатель)
                .status(UserStatus.Активный)
                .build();
        userRepository.save(user);
        ReaderTicket ticket = ReaderTicket.builder()
                .user(user)
                .build();
        readerTicketService.Save(ticket);
    }

    @Override
    public void block(User user) {
        user.setStatus(UserStatus.Заблокированный);
        userRepository.save(user);
    }

    @Override
    public void unblock(User user){
        user.setStatus(UserStatus.Активный);
        userRepository.save(user);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findFirstByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                roles
        );
    }
}
