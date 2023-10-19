package com.example.biblio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class SearchParamsDTO {
    private String authorId;
    private String genreId;
    private String publisherId;
    private String searchText;
}
