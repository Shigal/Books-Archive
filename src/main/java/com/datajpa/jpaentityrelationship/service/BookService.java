package com.datajpa.jpaentityrelationship.service;/*
 *
 * @author Lawshiga
 *
 */

import com.datajpa.jpaentityrelationship.dto.requestdto.BookRequestDto;
import com.datajpa.jpaentityrelationship.dto.responsedto.BookResponseDto;
import com.datajpa.jpaentityrelationship.model.Book;

import java.util.List;

public interface BookService {
    public BookResponseDto addBook(BookRequestDto bookRequestDto);
    public List<BookResponseDto> getBooks();
    public BookResponseDto getBookById(Long bookId);
    public Book getBook(Long bookId);
    public BookResponseDto deleteBook(Long bookId);
    public BookResponseDto editBook(Long bookId, BookRequestDto bookRequestDto);
    public BookResponseDto addAuthorToBook(Long bookId, Long authorId);
    public BookResponseDto deleteAuthorFromBook(Long bookId, Long authorId);
    public BookResponseDto addCategoryToBook(Long bookId, Long categoryId);
    public BookResponseDto deleteCategoryFromBook(Long bookId, Long categoryId);
}
