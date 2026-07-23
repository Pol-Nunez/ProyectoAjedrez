package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Mesa;

@Repository
public interface MesaRepository extends MongoRepository<Mesa, String> {
}