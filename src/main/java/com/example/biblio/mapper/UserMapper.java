package com.example.biblio.mapper;

import com.example.biblio.dto.UserDTO;
import com.example.biblio.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
    UserDTO fromAnyUser(User user);
}
