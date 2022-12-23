package com.datajpa.jpaentityrelationship.dto.responsedto;/*
 *
 * @author Lawshiga
 *
 */

import lombok.Data;

import java.util.List;

@Data
public class BookResponseDto {
    private long id;
    private String name;
    private List<String> authorNames;
    private String categoryName;
}
