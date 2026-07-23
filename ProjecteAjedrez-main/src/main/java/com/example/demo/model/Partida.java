package com.example.demo.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.model.Partida;

import io.swagger.v3.oas.annotations.media.Schema;

@Document(collection = "partidas")
@Schema(description = "Entity representing a chess match between two players")
public class Partida {

    @Id
    @Schema(
        description = "Unique identifier of the match",
        example = "65a8f1c23b9a"
    )
    private String id;

    @Schema(
        description = "Identifier of the first player",
        example = "player123"
    )
    private String player1Id;

    @Schema(
        description = "Identifier of the second player",
        example = "player456"
    )
    private String player2Id;

    @Schema(
        description = "Identifier of the winning player",
        example = "player123"
    )
    private String winnerId;

    @Schema(
        description = "Table number where the match is played",
        example = "5"
    )
    private Mesa table;

    @Schema(
        description = "Indicates whether the match has finished",
        example = "true"
    )
    private boolean finalitzada;

    public Partida(String id, String player1Id, String player2Id, String winnerId, Mesa table, boolean finalitzada) {
        this.id = id;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.winnerId = winnerId;
        this.table = table;
        this.finalitzada = finalitzada;
    }

    public Partida(String id, String player1Id, String player2Id, Mesa table, boolean finalitzada) {
        this.id = id;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.table = table;
        this.finalitzada = finalitzada;
    }
    
    public Partida() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayer1Id() {
        return player1Id;
    }

    public void setPlayer1Id(String player1Id) {
        this.player1Id = player1Id;
    }

    public String getPlayer2Id() {
        return player2Id;
    }

    public void setPlayer2Id(String player2Id) {
        this.player2Id = player2Id;
    }

    public String getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(String winnerId) {
        this.winnerId = winnerId;
    }

    public Mesa getTable() {
        return table;
    }

    public void setTable(Mesa table) {
        this.table = table;
    }

    public boolean isFinalitzada() {
        return finalitzada;
    }

    public void setFinalitzada(boolean finalitzada) {
        this.finalitzada = finalitzada;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Partida other = (Partida) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "Partida [id=" + id + ", player1Id=" + player1Id + 
               ", player2Id=" + player2Id + ", winner=" + winnerId + 
               ", table=" + table + "]";
    }
}