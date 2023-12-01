package com.example.biblio.service;

import com.example.biblio.dto.SearchParamsDTO;
import com.example.biblio.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl {
    public Page<Book> searchByCriteria(SearchParamsDTO dto){
        return null;
    }
}
