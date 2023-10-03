package com.example.biblio.mapper;

import com.example.biblio.dto.BookDTO;
import com.example.biblio.model.Book;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {
    BookMapper MAPPER = Mappers.getMapper(BookMapper.class);
    Book toProduct(BookDTO dto);
    @InheritInverseConfiguration
    BookDTO fromBook(Book book);
    List<Book> toProductList(List<BookDTO> bookDTOS);
    List<BookDTO> fromProductList(List<Book> products);
}
