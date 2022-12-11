package com.example.l08_jsonprocessing.service;

import com.example.l08_jsonprocessing.model.entity.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;

    public  Set<Category> findRandomCategories();
}
