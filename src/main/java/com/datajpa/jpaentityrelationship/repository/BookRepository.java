package com.datajpa.jpaentityrelationship.repository;/*
 *
 * @author Lawshiga
 *
 */

import com.datajpa.jpaentityrelationship.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
