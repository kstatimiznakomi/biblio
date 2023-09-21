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

    public UserServiceImpl(@Lazy BCryptPasswordEncoder encoder, @Lazy UserDAO userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @Override
    public User GetUserByUserName(String userName){
        return userRepository.getUserByUsername(userName);
    }

    @Override
    public void Save(UserDTO dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .password(encoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .role(UserRole.Читатель)
                .status(UserStatus.Активный)
                .build();
        userRepository.save(user);
        ReaderTicket ticket = ReaderTicket.builder()
                .user(user)
                .books(null)
                .build();
    }

    @Override
    public void Block(User user) {

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
