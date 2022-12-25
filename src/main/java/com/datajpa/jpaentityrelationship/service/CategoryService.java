package com.datajpa.jpaentityrelationship.service;/*
 *
 * @author Lawshiga
 *
 */

import com.datajpa.jpaentityrelationship.dto.requestdto.CategoryRequestDto;
import com.datajpa.jpaentityrelationship.dto.responsedto.CategoryResponseDto;
import com.datajpa.jpaentityrelationship.model.Category;

import java.util.List;

public interface CategoryService {
    public Category getCategory(Long categoryId);
    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto);
    public CategoryResponseDto getCategoryById(Long categoryId);
    public List<CategoryResponseDto> getCategories();
    public CategoryResponseDto deleteCategory(Long categoryId);
    public CategoryResponseDto editCategory(Long categoryId, CategoryRequestDto categoryRequestDto);
}
