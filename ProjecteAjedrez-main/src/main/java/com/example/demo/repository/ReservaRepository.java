package com.example.demo.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Mesa;
import com.example.demo.model.Reserva;


public interface ReservaRepository extends MongoRepository<Reserva, String>{

    Optional<Reserva> findFirstByMesaIdAndFechaAndHoraAndPartidaIsNull(
        String mesaId,
        LocalDate fecha,
        LocalTime hora
    );

}
