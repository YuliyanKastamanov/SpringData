package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.BookSummary;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);


    @Query("select b from Book as b where b.ageRestriction = :ageRestriction")
    List<Book> findByAgeRestriction(AgeRestriction ageRestriction);


    List<Book> findByEditionTypeAndCopiesLessThan(EditionType type, int copies);

    List<Book> findByPriceLessThanOrPriceGreaterThan(BigDecimal lowerBound, BigDecimal upperBound);


    @Query("select b from Book b where YEAR (b.releaseDate) not in :releaseYear")
    List<Book> findByReleaseDateYearNot(int releaseYear);

    List<Book> findByReleaseDateBefore(LocalDate date);

    List<Book> findByTitleContaining(String containedString);

    List<Book> findByAuthorLastNameStartingWith(String search);



    @Query("select count (b) from Book b where length(b.title) > :length")
    int countBooksWithTitleLongerThan(int length);

    @Query("select b.title as title, b.editionType as editionType, b.ageRestriction as ageRestriction, b.price as price from Book b " +
            "where b.title = :title")
    BookSummary findSummaryForTitle(String title);



    @Modifying
    @Transactional
    @Query("update Book b set b.copies = b.copies + :amount " +
            "where b.releaseDate > :after")
    int addCopiesToBooksAfter(LocalDate after, int amount);

    @Transactional
    int deleteByCopiesLessThan(int amount);
}

