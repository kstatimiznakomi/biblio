package com.example.biblio.service;

import com.example.biblio.model.User;
import com.example.biblio.model.UserStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class StatusManager {
    public Boolean ifUserMatchesStatus(User user, UserStatus status){
        return Objects.equals(user.getStatus(), status);
    }
}
