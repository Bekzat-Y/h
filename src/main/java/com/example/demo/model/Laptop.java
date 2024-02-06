package com.example.demo.model;

import jakarta.persistence.Basic;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Laptop {
    private Integer id;
    private String brand;
    private String model;
    private BigDecimal price;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] bookPhoto;
}
