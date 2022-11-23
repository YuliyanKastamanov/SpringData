package com.example.l05_springdataintro.repositories;

import com.example.l05_springdataintro.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    //List<Category> findByName(String name);
}
