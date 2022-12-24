package com.datajpa.jpaentityrelationship.repository;/*
 *
 * @author Lawshiga
 *
 */

import com.datajpa.jpaentityrelationship.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
