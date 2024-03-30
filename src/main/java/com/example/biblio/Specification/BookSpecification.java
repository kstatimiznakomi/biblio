package com.example.biblio.Specification;

import com.example.biblio.dto.SearchParamsDTO;
import com.example.biblio.model.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {
    public static Specification<Book> getById(SearchParamsDTO dto){
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("authorId"), dto.getAuthorId());
        });
    }
}
