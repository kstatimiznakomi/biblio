package com.example.biblio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookParamsDTO {
    private Long id;
    private String bookName;
    private String description;
    private String img;
    private Integer count;
    private String isbn;
    private Integer publicDate;
    private Long authorId;
    private Long publisherId;
    private Long genreId;
}
