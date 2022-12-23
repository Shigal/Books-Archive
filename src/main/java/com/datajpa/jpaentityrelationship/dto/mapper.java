package com.datajpa.jpaentityrelationship.dto;/*
 *
 * @author Lawshiga
 *
 */

import com.datajpa.jpaentityrelationship.dto.responsedto.AuthorResponseDto;
import com.datajpa.jpaentityrelationship.dto.responsedto.BookResponseDto;
import com.datajpa.jpaentityrelationship.dto.responsedto.CategoryResponseDto;
import com.datajpa.jpaentityrelationship.model.Author;
import com.datajpa.jpaentityrelationship.model.Book;
import com.datajpa.jpaentityrelationship.model.Category;

import java.util.ArrayList;
import java.util.List;

public class mapper {

    public static BookResponseDto bookToBookResponseDto(Book book){
        BookResponseDto response = new BookResponseDto();
        response.setId(book.getId());
        response.setName(book.getName());
        response.setCategoryName(book.getCategory().getName());
        List<String> names = new ArrayList<>();
        List<Author> authors = book.getAuthors();
        for(Author author : authors) {
            names.add(author.getName());
        }
        response.setAuthorNames(names);

        return response;
    }

    public static List<BookResponseDto> bookToBookResponseDtos(List<Book> books) {
        List<BookResponseDto> bookResponseDtos = new ArrayList<>();
        for(Book book: books) {
            bookResponseDtos.add(bookToBookResponseDto(book));
        }
        return bookResponseDtos;
    }

    public static AuthorResponseDto authorToAuthorResponseDto(Author author){
        AuthorResponseDto responseDto = new AuthorResponseDto();
        responseDto.setId(author.getId());
        responseDto.setName(author.getName());
        responseDto.setZipcodeName(author.getZipcode().getName());
        List<String> names = new ArrayList<>();
        List<Book> books = author.getBooks();
        for(Book book : books) {
            names.add(book.getName());
        }
        responseDto.setBookNames(names);

        return responseDto;
    }

    public static List<AuthorResponseDto> authorToAuthorResponseDtos(List<Author> authors) {
        List<AuthorResponseDto> responseDtoList = new ArrayList<>();
        for(Author author : authors) {
            responseDtoList.add(authorToAuthorResponseDto(author));
        }
        return responseDtoList;
    }

    public static CategoryResponseDto categoryToCategoryResponseDto(Category category) {
        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setId(category.getId());
        responseDto.setName(category.getName());
        List<String> names = new ArrayList<>();
        List<Book> books = category.getBooks();
        for(Book book: books) {
            names.add(book.getName());
        }
        responseDto.setBookNames(names);
        return responseDto;
    }

    public static List<CategoryResponseDto> categoryToCategoryResponseDtos(List<Category> categories) {
        List<CategoryResponseDto> responseDtoList = new ArrayList<>();
        for(Category category : categories) {
            responseDtoList.add(categoryToCategoryResponseDto(category));
        }
        return responseDtoList;
    }
}
