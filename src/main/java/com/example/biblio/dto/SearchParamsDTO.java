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
    private Long authorId;
    private Long genreId;
    private Long publisherId;
    private String searchText;
    private Integer publishDate;
    private Integer page;
}
