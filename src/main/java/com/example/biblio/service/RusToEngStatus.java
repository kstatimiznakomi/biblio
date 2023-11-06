package com.example.biblio.service;

import com.example.biblio.model.JournalNotes;
import com.example.biblio.model.NoteStatus;
import com.example.biblio.model.User;
import com.example.biblio.model.UserStatus;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

@Service
public class RusToEngStatus {
    public UserStatus rusToEng(User user){
        if (user.getStatus() == UserStatus.Активный) return UserStatus.Active;
        return UserStatus.Blocked;
    }
    public NoteStatus rusToEng(JournalNotes notes){
        if (notes.getStatus() == NoteStatus.Открытый) return NoteStatus.Open;
        return NoteStatus.Close;
    }
}
