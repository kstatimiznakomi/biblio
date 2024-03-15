package com.example.biblio.dao;

import com.example.biblio.dto.SearchCriteriaDTO;
import com.example.biblio.dto.SearchParamsDTO;
import com.example.biblio.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class BookRepositoryCustomImpl implements BookRepositoryCustom{
    EntityManager em;
    @Override
    public List<Book> getBooksByCriteries(SearchParamsDTO dto) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        /*CriteriaQuery<Book> cq = cb.createQuery(SearchParamsDTO.class);

        Root<Book> bookRoot = cq.from()*/


        return null;
    }

}
