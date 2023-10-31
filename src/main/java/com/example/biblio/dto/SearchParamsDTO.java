package com.example.biblio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchParamsDTO {
    private String authorId;
    private String genreId;
    private String publisherId;
    private String searchText;
}
