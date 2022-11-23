package com.example.l05_springdataintro.repositories;

import com.example.l05_springdataintro.entities.Author;
import com.example.l05_springdataintro.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {


    List<Book> findByReleaseDateAfter(LocalDate releaseDate);


}
