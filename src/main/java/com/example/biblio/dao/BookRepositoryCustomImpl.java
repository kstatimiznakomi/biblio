package com.example.biblio.dao;

import com.example.biblio.dto.SearchParamsDTO;
import com.example.biblio.model.Author;
import com.example.biblio.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom{
    EntityManager em;
    @Override
    public Page<Book> getBooksByCriteries(SearchParamsDTO dto) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Book> books = query.from(Book.class);
        if (dto.getAuthorId() != null){
            Join<Book, Author> authorJoin = books.join("authors", JoinType.INNER);
            predicates.add(cb.equal(authorJoin.get("id"), dto.getAuthorId()));
        }
        query.select(books).
                where(predicates.toArray(new Predicate[predicates.size()]));
        return new PageImpl<>(em.createQuery(query).getResultList());
    }
}
