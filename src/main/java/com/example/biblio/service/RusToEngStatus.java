package com.example.biblio.service;

import com.example.biblio.model.User;
import com.example.biblio.model.UserStatus;
import org.springframework.stereotype.Service;

@Service
public class RusToEngStatus {
    public UserStatus rusToEng(User user){
        if (user.getStatus() == UserStatus.Активный) return UserStatus.Active;
        return UserStatus.Blocked;
    }
}
