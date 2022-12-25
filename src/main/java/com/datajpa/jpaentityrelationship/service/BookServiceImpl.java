package com.datajpa.jpaentityrelationship.service;/*
 *
 * @author Lawshiga
 *
 */

import com.datajpa.jpaentityrelationship.dto.Mapper;
import com.datajpa.jpaentityrelationship.dto.requestdto.BookRequestDto;
import com.datajpa.jpaentityrelationship.dto.responsedto.BookResponseDto;
import com.datajpa.jpaentityrelationship.model.Author;
import com.datajpa.jpaentityrelationship.model.Book;
import com.datajpa.jpaentityrelationship.model.Category;
import com.datajpa.jpaentityrelationship.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }


    @Override
    public BookResponseDto addBook(BookRequestDto bookRequestDto) {
        Book book = new Book();
        book.setName(bookRequestDto.getName());
        if(bookRequestDto.getAuthorIds().isEmpty()) {
            throw new IllegalArgumentException("A book has at least one author.");
        }
        else {
            List<Author> authors = new ArrayList<>();
            for(Long authorId : bookRequestDto.getAuthorIds()) {
                authors.add(authorService.getAuthor(authorId));
            }
            book.setAuthors(authors);
        }
        if(bookRequestDto.getCategoryId() == null) {
            throw new IllegalArgumentException("A book belongs to a category.");
        }
        Category category = categoryService.getCategory(bookRequestDto.getCategoryId());
        book.setCategory(category);

        bookRepository.save(book);
        return Mapper.bookToBookResponseDto(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public BookResponseDto getBookById(Long bookId) {
        return null;
    }

    @Override
    public Book getBook(Long bookId) {
        return null;
    }

    @Override
    public BookResponseDto deleteBook(Long bookId) {
        return null;
    }

    @Override
    public BookResponseDto editBook(Long bookId, BookRequestDto bookRequestDto) {
        return null;
    }

    @Override
    public BookResponseDto addAuthorToBook(Long bookId, Long authorId) {
        return null;
    }

    @Override
    public BookResponseDto deleteAuthorFromBook(Long bookId) {
        return null;
    }

    @Override
    public BookResponseDto addCategoryToBook(Long bookId, Long categoryId) {
        return null;
    }

    @Override
    public BookResponseDto deleteCategoryFromBook(Long bookId) {
        return null;
    }
}
