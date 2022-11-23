package com.example.l05_springdataintro;

import com.example.l05_springdataintro.entities.Author;
import com.example.l05_springdataintro.entities.Book;
import com.example.l05_springdataintro.repositories.AuthorRepository;
import com.example.l05_springdataintro.repositories.BookRepository;
import com.example.l05_springdataintro.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ConsoleRunner implements CommandLineRunner {

    String FIRST_NAME = "George";
    String LAST_NAME = "Powell";

    private final SeedService seedService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.seedService = seedService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //this.seedService.seedAuthors();
        //this.seedService.seedCategories();

        //this.seedService.seedAll();

        //this.p01_booksAfter2000();
        //this.p02_allAuthorsWithBooksBefore1990();
        //this.p03_allAuthorsOrderedByBookCount();
        this.p04_allBooksByNamesOrderedDescByReleaseDateAndTitle();
    }

    private void p04_allBooksByNamesOrderedDescByReleaseDateAndTitle() {
        Author author = this.authorRepository.findByFirstNameAndLastName(FIRST_NAME, LAST_NAME);

       Set<Book> books = author.getBooks();

       List<Book> bookList = books.stream()
               .sorted((l, r) -> r.getReleaseDate().compareTo(l.getReleaseDate()))
               .toList();
        for (Book book : bookList) {

            System.out.println(book.getTitle() + " - " + book.getReleaseDate() + " - " + book.getCopies());
        }


    }


    private void p03_allAuthorsOrderedByBookCount() {

        List<Author> authors = this.authorRepository.findAll();

        authors.stream()
                .sorted((l, r) -> r.getBooks().size() - l.getBooks().size())
                .forEach(author ->
                                System.out.printf("%s %s -> %d%n",
                                        author.getFirstName(),
                                        author.getLastName(),
                                        author.getBooks().size())

                        );


    }

    private void p02_allAuthorsWithBooksBefore1990() {
        LocalDate year1990 = LocalDate.of(1990, 1, 1);


        List<Author> authors = this.authorRepository.findDistinctByBooksReleaseDateBefore(year1990);

        authors.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
    }

    private void p01_booksAfter2000() {

        LocalDate year2000 = LocalDate.of(2000, 12, 31);

        List<Book> books = this.bookRepository.findByReleaseDateAfter(year2000);

        books.forEach(b -> System.out.println(b.getTitle()));
    }

}
