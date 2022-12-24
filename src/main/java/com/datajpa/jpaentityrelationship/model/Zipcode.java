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
@Table(name = "zipcode")
public class Zipcode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city;

    public Zipcode(String name, City city) {
        this.name = name;
        this.city = city;
    }
}
