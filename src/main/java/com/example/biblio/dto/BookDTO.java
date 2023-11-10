package com.example.biblio.dto;

import com.example.biblio.model.Author;
import com.example.biblio.model.Publisher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    private Long id;
    private String bookName;
    private String description;
    private String img;
    private Integer count;
    private String isbn;
    private Integer publicDate;
}
