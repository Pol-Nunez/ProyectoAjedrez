package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Mesa;
import com.example.demo.repository.MesaRepository;

@Service
public class MesaServiceImpl implements MesaService {
	
	@Autowired
    private final MesaRepository mesaRepo;

    public MesaServiceImpl(MesaRepository mesaRepo) {
        this.mesaRepo = mesaRepo;
    }

    @Override
    public Optional<Mesa> findById(String id) {
        return mesaRepo.findById(id);
    }

    @Override
    public List<Mesa> findAll() {
        return mesaRepo.findAll();
    }

    @Override
    public Mesa save(Mesa mesa) {
        return mesaRepo.save(mesa);
    }

    @Override
    public boolean isDisponible(String id) {
        Optional<Mesa> optionalMesa = mesaRepo.findById(id);
        if (optionalMesa.isPresent()) {
            Mesa mesa = optionalMesa.get();
            return mesa.isDisponibilidad();
        } else {
            throw new IllegalArgumentException("Mesa no encontrada con id: " + id);
        }
    }

    @Override
    public boolean deleteById(String id) {
        if (mesaRepo.existsById(id)) {
            mesaRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}