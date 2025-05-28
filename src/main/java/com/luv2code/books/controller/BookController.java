package com.luv2code.books.controller;

import com.luv2code.books.entity.Books;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    public BookController() {
        initializeBooks();
    }

    private final List<Books> books = new ArrayList<>();

    private void initializeBooks() {
        books.addAll(List.of(
                new Books(1, "Title one", "Author one", "science", 5),
                new Books(2, "Title two", "Author two", "science", 3),
                new Books(3, "Title three", "Author three", "history", 3),
                new Books(4, "Title four", "Author four", "math", 4),
                new Books(5, "Title five", "Author five", "math", 2),
                new Books(6, "Title six", "Author six", "math", 1)
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

    @GetMapping("/{id}")
    public Books getBookByTitle(@PathVariable long id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Query Parameters

//    @GetMapping("api/books")
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

    @GetMapping
    public List<Books> getBooks(@RequestParam(required = false) long id) {
        if(id <= 0) {
            return books;
        }

        return books.stream().filter(book -> book.getId() == id)
                .toList();
    }

    // Post Request

//    @PostMapping("/api/books")
//    public void createBook(@RequestBody Books newBook) {
//        for (Books book: books) {
//            if (book.getTitle().equalsIgnoreCase(newBook.getTitle())) {
//                return;
//            }
//        }
//        books.add(newBook);
//    }

    @PostMapping
    public void createBook(@RequestBody Books newBook) {
        boolean isNewBook = books.stream()
                        .noneMatch(book -> book.getId() <= newBook.getId());
        if (isNewBook){
            books.add(newBook);
        }
    }

    // Put Request

    @PutMapping("/{id}")
    public void updateBook (@PathVariable long id, @RequestBody Books updateBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
               books.set(i, updateBook);
               return;
            }
        }
    }

    // Delete Request

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id) {
        books.removeIf(book -> book.getId() == id);
    }

}






















