package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table
public class Readers {
    @Id
    @GeneratedValue

    private int id;
    private String firstName;
    private String lastName;
    private String birthDay;
}
