package com.example.springintro.repository;

import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.AuthorsNamesWithTotalCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a ORDER BY a.books.size DESC")
    List<Author> findAllByBooksSizeDESC();

    List<Author> findAuthorsByFirstNameEndsWith(String endsWith);



    @Query("select a.firstName as firstName, a.lastName as lastName , sum (b.copies) as totalCopies from Author a " +
            "join a.books as b " +
            "group by b.author " +
            "order by totalCopies desc")
    List<AuthorsNamesWithTotalCount> getWithTotalCopies();
}
