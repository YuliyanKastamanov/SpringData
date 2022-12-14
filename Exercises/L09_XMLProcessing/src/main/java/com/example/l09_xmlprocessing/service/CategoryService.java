package com.example.l09_xmlprocessing.service;

import com.example.l09_xmlprocessing.model.dto.CategorySeedDto;
import com.example.l09_xmlprocessing.model.entity.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    void seedCategories(List<CategorySeedDto> categories);

    long getEntityCount();

    Set<Category> getRandomCategories();
}
