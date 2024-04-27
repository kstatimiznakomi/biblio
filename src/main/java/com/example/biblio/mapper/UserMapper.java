package com.example.biblio.mapper;

import com.example.biblio.dto.UserDTO;
import com.example.biblio.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
    UserDTO fromUser(User user);

    List<UserDTO> fromUserList(List<User> user);
}
