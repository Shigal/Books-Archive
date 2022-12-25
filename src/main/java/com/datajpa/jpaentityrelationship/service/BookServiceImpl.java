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

import javax.transaction.Transactional;
import java.nio.channels.IllegalChannelGroupException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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


    @Transactional
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
    public List<BookResponseDto> getBooks() {
        List<Book> books = StreamSupport
                .stream(bookRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return Mapper.bookToBookResponseDtos(books);
    }

    @Override
    public BookResponseDto getBookById(Long bookId) {
        Book book = getBook(bookId);
        return Mapper.bookToBookResponseDto(book);
    }

    @Override
    public Book getBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() ->
                new IllegalArgumentException("Cannot find a book with id: " + bookId));
        return book;
    }

    @Override
    public BookResponseDto deleteBook(Long bookId) {
        Book book = getBook(bookId);
        bookRepository.delete(book);
        return Mapper.bookToBookResponseDto(book);
    }

    @Transactional
    @Override
    public BookResponseDto editBook(Long bookId, BookRequestDto bookRequestDto) {
        Book bookToEdit = getBook(bookId);
        bookToEdit.setName(bookRequestDto.getName());
        if(!bookRequestDto.getAuthorIds().isEmpty()) {
            List<Author> authors = new ArrayList<>();
            for(Long authorId: bookRequestDto.getAuthorIds()) {
                authors.add(authorService.getAuthor(authorId));
            }
            bookToEdit.setAuthors(authors);
        }
        if(bookRequestDto.getCategoryId() != null) {
            Category category = categoryService.getCategory(bookRequestDto.getCategoryId());
            bookToEdit.setCategory(category);
        }
        return Mapper.bookToBookResponseDto(bookToEdit);
    }

    @Override
    public BookResponseDto addAuthorToBook(Long bookId, Long authorId) {
        Book book = getBook(bookId);
        Author author = authorService.getAuthor(authorId);
        if(author.getBooks().contains(book)) {
            throw new IllegalArgumentException("This author is already added to this book.");
        }
        // bidirectional relationship
        book.addAuthor(author);
        author.addBook(book);
        return Mapper.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto deleteAuthorFromBook(Long bookId, Long authorId) {
        Book book = getBook(bookId);
        Author author = authorService.getAuthor(authorId);
        if(!author.getBooks().contains(book)) {
            throw new IllegalArgumentException("This book doesn't have such author.");
        }
        author.removeBook(book);
        book.removeAuthor(author);

        return Mapper.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto addCategoryToBook(Long bookId, Long categoryId) {
        Book book = getBook(bookId);
        Category category = categoryService.getCategory(categoryId);
        if(Objects.nonNull(book.getCategory())) {
            throw new IllegalArgumentException("This book already belongs to a category.");
        }
        book.setCategory(category);
        category.addBook(book);

        return Mapper.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto deleteCategoryFromBook(Long bookId, Long categoryId) {
        Book book = getBook(bookId);
        Category category = categoryService.getCategory(categoryId);
        if(!Objects.nonNull(book.getCategory())) {
            throw new IllegalArgumentException("This book does not have a category to delete.");
        }
        book.setId(null);
        category.removeBook(book);

        return Mapper.bookToBookResponseDto(book);
    }
}
