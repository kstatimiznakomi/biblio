package com.example.biblio.dto;

import com.example.biblio.model.Book;
import com.example.biblio.model.NoteStatus;
import com.example.biblio.model.ReaderTicket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JournalNotesDTO {
    private Long id;
    private Date dateTake;
    private Date dateReturn;
    private Long readerTicket;
    private Long book;
    private String status;
}
