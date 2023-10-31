package com.example.biblio.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PageService {
    public Integer GetBiggerLower(int page, int totalPage){
        if (page < 1) return 1;
        if (page > totalPage) return totalPage;
        return page;
    }

    public PageRequest getPage(int pageNumber){
        return PageRequest.of(pageNumber - 1,10);
    }
}
