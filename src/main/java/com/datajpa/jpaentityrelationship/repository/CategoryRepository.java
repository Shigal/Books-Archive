package com.datajpa.jpaentityrelationship.repository;/*
 *
 * @author Lawshiga
 *
 */

import com.datajpa.jpaentityrelationship.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
