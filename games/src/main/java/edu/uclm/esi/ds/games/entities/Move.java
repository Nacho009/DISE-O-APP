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
    private int col1;
    @Column(nullable = false)
    private int row2;
    @Column(nullable = false)
    private int col2;

    
    public Move(Long id, Game game, User player, int row1, int col1,int row2, int col2) {
        this.id = id;
        this.game = game;
        this.player = player;
        this.row1 = row1;
        this.col1 = col1;
        this.row2 = row2;
        this.col2 = col2;
    }
    public Move(){

    }

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

    public int getCol1() {
        return col1;
    }

    public void setCol1(int col1) {
        this.col1 = col1;
    }
    public int getRow2() {
        return row2;
    }
    public void setRow2(int row2) {
        this.row2 = row2;
    }
    public int getCol2() {
        return col2;
    }
    public void setCol2(int col2) {
        this.col2 = col2;
    }

    
}
