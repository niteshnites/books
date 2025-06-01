package com.luv2code.books.controller;

import com.luv2code.books.entity.Books;
import com.luv2code.books.exception.BookNotFoundException;
import com.luv2code.books.request.BookRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Books Rest API Endpoints", description = "Operation related to books")
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
    @Operation(summary = "Get all books", description = "Retrieve all available books")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Books getBookById(@Parameter(description = "Id of the book to be retrieve") @PathVariable @Min(value = 1) long id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book not found - " + id));
    }

    @Operation(summary = "Get a book by Id", description = "Retrieve a specific book by Id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Books> getBooks(@Parameter(description = "Optional query parameter")
                                    @RequestParam(required = false) String title) {
        if(title == null) {
            return books;
        }

        return books.stream().filter(book -> book.getTitle().equalsIgnoreCase(title))
                .toList();
    }

    // Post Request
    @Operation(summary = "Create a new Book", description = "Add new book to the list")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createBook(@Valid @RequestBody BookRequest bookRequest) {
        long id =  books.isEmpty() ? 1 : books.get(books.size()-1).getId() + 1;
        Books book = convertToBook(id, bookRequest);
        books.add(book);
    }

    // Put Request
    @Operation(summary = "Update a Book", description = "Update the details of an existing book")
    @PutMapping("/{id}")
    public void updateBook(@Parameter(description = "Id of the book to update") @PathVariable @Min(value = 1) long id, @Valid @RequestBody BookRequest bookRequest) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                Books updateBook = convertToBook(id, bookRequest);
                books.set(i, updateBook);
                return;
            }
        }
    }

    // Delete Request
    @Operation(summary = "Delete a new Book", description = "Remove a book from the list")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBook(@Parameter(description = "Id of the book to delete") @PathVariable long id) {
        books.removeIf(book -> book.getId() == id);
    }

    // Convert to Book
    public Books convertToBook(long id, BookRequest bookRequest) {
        return new Books(
                id,
                bookRequest.getTitle(),
                bookRequest.getAuthor(),
                bookRequest.getCategory(),
                bookRequest.getRating()
        );
    }



}






















