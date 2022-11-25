package com.example.springintro;

import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.BookSummary;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        //seedData();

        //printAllBooksAfterYear(2000);
        //printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
        //printAllAuthorsAndNumberOfTheirBooks();
        //printALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");

        // exc.01
        /*
        String restriction = scanner.nextLine();

        this.bookService.findAllTitlesByAgeRestriction(restriction)
                .forEach(System.out::println);*/



        // exc.02

        /*this.bookService.findAllTitlesByEditionAndCopies(EditionType.GOLD, 5000)
                .forEach(System.out::println);*/


        // exc.03

        /*this.bookService.findByPriceLessThanOrPriceGreaterThan(5, 40)
                .forEach(b -> System.out.println(b.getTitle() + " - " + b.getPrice()));*/


        // exc.04

        /*int releaseYear = Integer.parseInt(scanner.nextLine());
        bookService.findByReleaseDateYearNot(releaseYear).forEach(b -> System.out.println(b.getTitle()));*/


        // exc.05
        /*String input = scanner.nextLine();

        bookService.findByReleaseDateBefore(input)
                .forEach(b -> System.out.println(b.getTitle() + " " + b.getEditionType() + " " + b.getPrice()));*/


        // exc.06
        /*String endWith = scanner.nextLine();

        authorService.findAuthorsByFirstNameEndsWith(endWith)
                .forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));*/

        // exc.07

        /*String containedString = scanner.nextLine();

        bookService.findByTitleContaining(containedString)
                .forEach(System.out::println);*/



        // exc.08
        /*String search = scanner.nextLine();

        this.bookService.findByAuthorLastNameStartsWith(search)
                .forEach(b -> System.out.printf("%s (%s %s)%n",
                b.getTitle(),
                b.getAuthor().getFirstName(),
                b.getAuthor().getLastName()));*/



        // exc.09

        /*int length = Integer.parseInt(scanner.nextLine());

        int totalBooks = this.bookService.countBooksWithTitleLongerThan(length);

        System.out.println(totalBooks);*/



        // exc.10
        /*this.authorService.getWithTotalCopies()
                .forEach(a -> System.out.println(
                        a.getFirstName() + " " +
                        a.getLastName() + " " +
                        a.getTotalCopies()));*/


        // exc.11
        /*String title = scanner.nextLine();

        BookSummary summary = this.bookService.getInformationForTitle(title);

        System.out.println(summary.getTitle() + " " + summary.getEditionType() + " " +
                summary.getAgeRestriction() + " " + summary.getPrice());*/


        // exc.12*
        /*String date = scanner.nextLine();
        int amount = Integer.parseInt(scanner.nextLine());

        int booksUpdated = this.bookService.addCopiesToBooksAfter(date, amount);

        System.out.printf("%d books are released after %s, so total of %d book copies were added%n",
                booksUpdated, date, amount * booksUpdated);*/


        // exc.13*

        /*int amount = Integer.parseInt(scanner.nextLine());
        int deletedCount = this.bookService.deleteWithCopiesLessThanAmount(amount);

        System.out.println(deletedCount + "books were deleted");*/





    }










    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
