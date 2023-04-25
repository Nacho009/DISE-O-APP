package edu.uclm.esi.ds.games.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity 
@Table(
		name = "movements")
public class Move {
    
    @Id
    private Long id;

    @ManyToOne
    private Game game;
    @OneToOne
    private User player;
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

   

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public User getPlayer() {
        return player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }

    public int getRow1() {
        return row1;
    }

    public void setRow1(int row1) {
        this.row1 = row1;
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
