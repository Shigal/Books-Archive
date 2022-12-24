package com.datajpa.jpaentityrelationship.repository;/*
 *
 * @author Lawshiga
 *
 */

import com.datajpa.jpaentityrelationship.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
}
