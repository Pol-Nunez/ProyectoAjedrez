package com.example.demo.service;

import com.example.demo.model.Mesa;
import java.util.List;
import java.util.Optional;

public interface MesaService {

    Optional<Mesa> findById(String id);

    List<Mesa> findAll();

    Mesa save(Mesa mesa);

    boolean isDisponible(String id);

    boolean deleteById(String id);
}