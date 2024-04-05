package com.example.biblio.Controller;

import com.example.biblio.model.JournalNotes;
import com.example.biblio.model.User;
import com.example.biblio.service.JournalNotesService;
import com.example.biblio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
public class ProfileAPI {
    private final JournalNotesService notesService;
    private final UserService userService;
    @GetMapping("/profile/api")
    public User getCurrUser(Principal principal){
        return userService.getUserByName(principal.getName());
    }

    @GetMapping("/profile/get-notes")
    public List<JournalNotes> notes (Principal principal){
        List<JournalNotes> notes = notesService.getReadBooks(principal);
        System.out.println(notes);
        return notes;
    }
}
