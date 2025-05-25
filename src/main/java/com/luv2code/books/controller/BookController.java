package com.luv2code.books.controller;

import com.luv2code.books.entity.Books;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    public BookController() {
        initializeBooks();
    }

    private final List<Books> books = new ArrayList<>();

    private void initializeBooks() {
        books.addAll(List.of(
                new Books("Title one", "Author one", "science"),
                new Books("Title two", "Author two", "science"),
                new Books("Title three", "Author three", "history"),
                new Books("Title four", "Author four", "math"),
                new Books("Title five", "Author five", "math"),
                new Books("Title six", "Author six", "math")
        ));
    }

    @GetMapping("/api")
    public List<Books> getBooks() {
        return books;
    }

}
