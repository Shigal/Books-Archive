package com.datajpa.jpaentityrelationship.model;/*
 *
 * @author Lawshiga
 *
 */

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    public City(String name) {
        this.name = name;
    }
}
