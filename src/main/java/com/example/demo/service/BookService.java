package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookService {
    private String URL = "jdbc:postgresql://localhost:5432/BookStore";
    private String USER = "postgres";
    private String PASSWORD = "postgres";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    private final BookRepo bookRepository;

    @Autowired
    public BookService(BookRepo bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void saveBook(Book bookRequest) {
        Book book = new Book();
        book.setBookName(bookRequest.getBookName());
        book.setAuthorName(bookRequest.getAuthorName());
        book.setPublishDate(new Date());
        book.setBookPhoto(bookRequest.getBookPhoto());
        bookRepository.save(book);
    }

    public void insertBook(Book book) {
        String sql = "INSERT INTO Book (author_name, book_name, publish_dat) VALUES (?, ?, ?,?)";
        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, book.getAuthorName());
            statement.setString(2, book.getBookName());
            statement.setDate(3, (java.sql.Date) book.getPublishDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }







    public void deleteBook(int bookId) {
        String sql = "DELETE FROM Book WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, bookId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBook(Integer id, Book book) {
        String sql = "UPDATE Book SET author_name = ?, book_name = ?, publish_data = ? WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, book.getAuthorName());
            statement.setString(2, book.getBookName());
            statement.setDate(3, (java.sql.Date) book.getPublishDate());
            statement.setInt(4,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

//         String base64EncodedPhoto = encodeToBase64(bookRequest.getBookPhoto());
//         book.setBookPhoto(base64EncodedPhoto);
//         } else {
//         book.setBookPhoto(bookRequest.getBookPhoto());
//         }
//
//         bookRepository.save(book);
//private String encodeToBase64(byte[] data) {
//    return Base64.getEncoder().encodeToString(data);
//}