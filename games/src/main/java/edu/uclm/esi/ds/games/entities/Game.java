package edu.uclm.esi.ds.games.entities;

import java.util.ArrayList;


import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(
		name = "games")
public class Game {
    @Id
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="id")
    private User player1;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="id")
    private User player2;

    private ArrayList<Move> movements;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="id")
    private User winner;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="id")
    private User loser;

    

    // Getters y setters

    // public Game(Long id, User player1, User player2, ArrayList<Move> movements, User winner, User loser) {

    //     this.player1 = player1;
    //     this.player2 = player2;
    //     this.movements = movements;
    //     this.winner = winner;
    //     this.loser = loser;
    // }

    // public Game(){
    // }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getPlayer1() {
        return player1;
    }

    public void setPlayer1(User player1) {
        this.player1 = player1;
    }

    public User getPlayer2() {
        return player2;
    }

    public void setPlayer2(User player2) {
        this.player2 = player2;
    }

    public ArrayList<Move> getMovements() {
        return movements;
    }

    public void setMovements(ArrayList<Move> movements) {
        this.movements = movements;
    }

    public User getWinner() {
        return winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    public User getLoser() {
        return loser;
    }

    public void setLoser(User loser) {
        this.loser = loser;
    }

}
