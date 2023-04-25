package edu.uclm.esi.ds.games.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(
		name = "movements")
public class Move {
    
    @Id
    private Long id;

    @Column(nullable = false)
    private Long gameId;
    @Column(nullable = false)
    private Long playerId;
    @Column(nullable = false)
    private int row1;
    @Column(nullable = false)
    private int col;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public int getRow() {
        return row1;
    }

    public void setRow(int row) {
        this.row1 = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    
}
