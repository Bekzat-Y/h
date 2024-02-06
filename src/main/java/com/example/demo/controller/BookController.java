package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.model.Laptop;
import com.example.demo.service.BookService;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.Laptop;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/uploadPhoto")
    public ResponseEntity<String> saveBook(@RequestBody Book bookRequest) {
            bookService.saveBook(bookRequest);
            return ResponseEntity.ok("Book saved successfully!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book deleted successfully!");
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertBook(@RequestBody Book book){
        bookService.insertBook(book);
        return ResponseEntity.ok("Создан");
    }
    @PutMapping("/updateResource/{id}")
    public ResponseEntity<String>updateResource(@PathVariable int id , @RequestBody Book book){
        bookService.updateBook(id , book);
        return ResponseEntity.ok("update");
    }



}
