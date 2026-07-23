package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.model.Partida;

public interface PartidaRepository extends MongoRepository<Partida, String>{

    List<Partida> findAllByPlayer1Id(String player1Id);

    List<Partida> findAllByPlayer2Id(String player2Id);

    List<Partida> findAllByWinnerId(String winnerId);


    List<Partida> findAllByWinnerIdNot(String winnerId);

    @Query("{ '$or': [ { 'player1Id': ?0 }, { 'player2Id': ?0 } ] }")
    List<Partida> findAllByPlayer(String playerId);

    @Query("{ '$and': [ { '$or': [ { 'player1Id': ?0 }, { 'player2Id': ?0 } ] }, { 'winnerId': { '$ne': ?1 } } ] }")
    List<Partida> findAllByPlayerAndWinnerIdNot(String playerId, String winnerId);
}