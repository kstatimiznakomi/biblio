package com.example.biblio.service;

import com.example.biblio.model.Book;
import org.springframework.data.domain.Page;
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
        return PageRequest.of(pageNumber - 1,5);
    }

    public Integer Min(int currentPage){
        if (currentPage - 5 < 1) return 1;
        return currentPage - 5;
    }

    public Integer Max(int currentPage, int totalPage){
        if (currentPage + 5 > totalPage) return totalPage;
        return currentPage + 5;
    }

    public Boolean toDraw(long totalElems){
        return totalElems > 0;
    }
}
