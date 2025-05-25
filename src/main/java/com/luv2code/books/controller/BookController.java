package com.luv2code.books.controller;

import com.luv2code.books.entity.Books;
import org.springframework.web.bind.annotation.*;

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

    // Get Request

//    @GetMapping("/api/allBooks")
//    public List<Books> getAllBooks() {
//        return books;
//    }

    // Path Variables

//    @GetMapping("/api/books/{title}")
//    public Books getBookByTitle(@PathVariable String title) {
//        for(Books book: books) {
//            if(book.getTitle().equalsIgnoreCase(title)) {
//                return book;
//            }
//        }
//        return null;
//    }

    @GetMapping("/api/books/{title}")
    public Books getBookByTitle(@PathVariable String title) {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    // Query Parameters

//    @RequestMapping("api/books")
//    public List<Books> getBooks(@RequestParam(required = false) String category) {
//        if(category == null) {
//            return books;
//        }
//        List<Books> filterBook = new ArrayList<>();
//        for (Books book: books) {
//            if (book.getCategory().equalsIgnoreCase(category)) {
//                filterBook.add(book);
//            }
//        }
//        return filterBook;
//    }

    @RequestMapping("api/books")
    public List<Books> getBooks(@RequestParam(required = false) String category) {
        if(category == null) {
            return books;
        }

        return books.stream().filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();
    }

}






















