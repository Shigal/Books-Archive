package com.datajpa.jpaentityrelationship.repository;/*
 *
 * @author Lawshiga
 *
 */

import com.datajpa.jpaentityrelationship.model.Zipcode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipcodeRepository extends CrudRepository<Zipcode, Long> {
}
