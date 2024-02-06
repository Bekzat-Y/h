package com.example.demo.dao;

import com.example.demo.model.Laptop;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class LaptopDao {
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

    public void insertLaptop(Laptop laptop) {
        String sql = "INSERT INTO laptops (brand, model, price,photo_path) VALUES (?, ?, ?,?)";
        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, laptop.getBrand());
            statement.setString(2, laptop.getModel());
            statement.setBigDecimal(3, laptop.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Laptop> getAllLaptops(){
        List<Laptop> laptops = new ArrayList<>();

        String SQL = "select * from laptops";

        try(Connection connection = connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL)){

            while(resultSet.next()){
                Laptop laptop = new Laptop();

                laptop.setId(resultSet.getInt(1));
                laptop.setBrand(resultSet.getString(2));
                laptop.setModel(resultSet.getString(3));
                laptop.setPrice(resultSet.getBigDecimal(4));

                laptops.add(laptop);
            }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return laptops;
    }

    public void deleteLaptop(int laptopId) {
        String sql = "DELETE FROM laptops WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, laptopId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateLaptop(Integer id, Laptop laptop) {
        String sql = "UPDATE laptops SET brand = ?, model = ?, price = ? WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, laptop.getBrand());
            statement.setString(2, laptop.getModel());
            statement.setBigDecimal(3, laptop.getPrice());
            statement.setInt(4,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


