package com.example.biblio.service;

import com.example.biblio.dao.UserDAO;
import com.example.biblio.dto.UserDTO;
import com.example.biblio.mapper.UserMapper;
import com.example.biblio.model.ReaderTicket;
import com.example.biblio.model.User;
import com.example.biblio.model.UserRole;
import com.example.biblio.model.UserStatus;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder encoder;
    private final UserDAO userRepository;
    private final ReaderTicketService readerTicketService;
    private final UserMapper userMapper = UserMapper.MAPPER;

    @Override
    public User getUserByName(String name){
        return userRepository.findFirstByUsername(name);
    }

    @Override
    public User getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

    @Override
    public UserDTO getUser(String name) {
        return userMapper.fromUser(userRepository.getUserByUsername(name));
    }

    @Override
    public Boolean checkUserForExist(String name){
        return userRepository.findFirstByUsername(name) != null;
    }

    @Override
    public Boolean ifUserSigned(Principal principal){
        return principal != null;
    }

    @Override
    public User create(UserDTO dto){
        return User.builder()
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
    }



    @Override
    public void Save(User user){
        userRepository.save(user);
        readerTicketService.Create(user);
    }

    @Override
    public void Save(UserDTO dto) {
        User user = create(dto);
        userRepository.save(user);
        readerTicketService.Create(user);
    }

    @Override
    public void patchUser(UserDTO dto, User user){
        user.setName(dto.getName());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setLastname(dto.getLastname());
        user.setSurname(dto.getSurname());
        user.setAddress(dto.getAddress());
        user.setUsername(dto.getUsername());
        user.setPhone(dto.getPhone());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findFirstByUsername(username);
        if (user == null) {
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

    @Override
    public List<UserDTO> getAllUsers() { return userMapper.fromUserList(userRepository.findAll()); }

    public void save(User user) {
        if (user.getId() != null)
            userRepository.save(user);
    }
}
