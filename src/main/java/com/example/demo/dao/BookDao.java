package com.example.demo.dao;

import com.example.demo.model.Book;
import com.example.demo.model.Laptop;
import com.example.demo.model.Laptop;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class BookDao {
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
    public void insertBook(Book book) {
        String sql = "INSERT INTO Book (author_name, book_name, publish_dat) VALUES (?, ?, ?,?)";
        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, book.getAuthorName());
            statement.setString(2, book.getBookName());
            statement.setDate(3, (Date) book.getPublishDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




                public List<Book> getAll(){
                    List<Book> books = new ArrayList<>();

                    String SQL = "select * from Book";

                    try(Connection connection = connect();
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(SQL)){

                        while(resultSet.next()){
                            Book book = new Book();

                            book.setId(resultSet.getInt(1));
                            book.setAuthorName(resultSet.getString(2));
                            book.setBookName(resultSet.getString(3));
                            book.setPublishDate(resultSet.getDate(4));

                            books.add(book);
                        }
                    }catch (SQLException sqlException){
                        System.out.println(sqlException.getMessage());
                    }
                    return books;
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
            statement.setDate(3, (Date) book.getPublishDate());
            statement.setInt(4,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
