package com.example.biblio.dao;

import com.example.biblio.dto.SearchParamsDTO;
import com.example.biblio.model.Author;
import com.example.biblio.model.Book;
import com.example.biblio.model.Genres;
import com.example.biblio.model.Publisher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom{
    EntityManager em;
    @Override
    public Page<Book> getBooksByCriteries(SearchParamsDTO dto, Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Book> books = query.from(Book.class);
        int pageSize = 5;
        int start = 0;
        for (int i = 1; i < dto.getPage(); i++){
            start += pageSize;
        }

        if (dto.getSearchText() != null){
            predicates.add(cb.like(cb.upper(books.get("bookName")), "%" + dto.getSearchText().toUpperCase() + "%"));
        }

        if (dto.getPublishDate() != null){
            predicates.add(cb.equal(books.get("publicDate"), dto.getPublishDate()));
        }

        if (dto.getAuthorId() != null){
            Join<Book, Author> authorJoin = books.join("authors", JoinType.INNER);
            predicates.add(cb.equal(authorJoin.get("id"), dto.getAuthorId()));
        }
        if (dto.getGenreId() != null){
            Join<Book, Genres> genresJoin = books.join("genres", JoinType.INNER);
            predicates.add(cb.equal(genresJoin.get("id"), dto.getGenreId()));
        }
        if (dto.getPublisherId() != null){
            Join<Book, Publisher> publisherJoin = books.join("publisher", JoinType.RIGHT);
            predicates.add(cb.equal(publisherJoin.get("id"), dto.getPublisherId()));
        }
        int totalElements = em.createQuery(query).getResultList().size();
        System.out.println(totalElements);
        query.select(books).
                where(predicates.toArray(new Predicate[predicates.size()]));

        TypedQuery<Book> typedQuery = em.createQuery(query);
        typedQuery.setFirstResult((int)pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());
        List<Book> bookss = typedQuery.getResultList();

        return PageableExecutionUtils.getPage(
                bookss, pageable, () -> totalElements
        );
        /*return new PageImpl<>(em.createQuery(query)
                .setFirstResult(start)
                .setMaxResults(pageSize)
                .getResultList());*/
    }
}
