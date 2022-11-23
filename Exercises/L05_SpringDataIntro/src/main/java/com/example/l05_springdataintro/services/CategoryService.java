package com.example.l05_springdataintro.services;


import com.example.l05_springdataintro.entities.Category;

import java.util.Set;

public interface CategoryService {

    Set<Category> getRandomCategories();
}
