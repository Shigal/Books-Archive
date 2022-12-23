package com.datajpa.jpaentityrelationship.dto.requestdto;/*
 *
 * @author Lawshiga
 *
 */

import lombok.Data;

import java.util.List;

@Data
public class BookRequestDto {
    private String name;
    private List<Long> authorIds;
    private long categoryId;
}
