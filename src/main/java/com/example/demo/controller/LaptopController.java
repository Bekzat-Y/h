package com.example.demo.controller;

import com.example.demo.dao.LaptopDao;
import com.example.demo.model.Laptop;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/laptops")

public class LaptopController {

    private final LaptopDao dao;




    @GetMapping("/getAllLaptops")
    public List<Laptop> getAllLaptops(){
        return dao.getAllLaptops();
    }


    @PostMapping("/insertLaptop")
    public ResponseEntity<String> insertLaptop(@RequestBody Laptop laptop){
        dao.insertLaptop(laptop);
        return ResponseEntity.ok("Добавлен");
    }

    @DeleteMapping("deleteLaptop/{laptopId}")
    public ResponseEntity<String> deleteLaptop(@PathVariable int laptopId){
        dao.deleteLaptop(laptopId);
        return ResponseEntity.ok("Удвлен");
    }

    @PutMapping("/updateResource/{id}")
    public ResponseEntity<String> updateResource(@PathVariable int id, @RequestBody Laptop updateDto) {
        dao.updateLaptop(id, updateDto);
        return ResponseEntity.ok("Ресурс успешно обновлен");
    }

}
