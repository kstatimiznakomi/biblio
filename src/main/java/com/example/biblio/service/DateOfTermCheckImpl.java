package com.example.biblio.service;

import com.example.biblio.model.JournalNotes;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class DateOfTermCheckImpl implements DateOfTermCheck{
    private final JournalNotesService notesService;
    private final UserService userService;
    @Override
    public void checkForBlock() {
        List<JournalNotes> allOpenNotes = notesService.getAllNotesWithOpenStatus();
        for (JournalNotes note : allOpenNotes){
            if (Objects.equals(note.getDateReturn(), LocalDateTime.now())){
                userService.block(note.getReaderTicket().getUser());
            }
        }
    }
}
