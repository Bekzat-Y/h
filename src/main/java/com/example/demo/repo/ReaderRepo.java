package com.example.demo.repo;

import com.example.demo.model.Readers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepo extends JpaRepository<Readers, Integer> {
}
