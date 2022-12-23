package com.datajpa.jpaentityrelationship.model;/*
 *
 * @author Lawshiga
 *
 */

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "zipcode_id")
    private Zipcode zipcode;

    @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private List<Book> books = new ArrayList<>();

    public Author(String name, Zipcode zipcode, List<Book> books) {
        this.name = name;
        this.zipcode = zipcode;
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);

    }}