package com.example.springintro.service;

import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.AuthorsNamesWithTotalCount;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfTheirBooks();

    List<Author> findAuthorsByFirstNameEndsWith(String endWith);

    List<AuthorsNamesWithTotalCount> getWithTotalCopies();
}
