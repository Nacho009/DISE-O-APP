package edu.uclm.esi.ds.games.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity 
@Table(
		name = "games")
public class Game {
    @Id
    private String id;

    @OneToOne
    private User player1;
    @OneToOne
    private User player2;


    @OneToMany(mappedBy = "game")
    private List<Move> movements;
    @OneToOne
    private User winner;
    @OneToOne
    private User loser;

    

    // Getters y setters

    public Game(String id, User player1, User player2) {

        this.id=id;
        this.player1 = player1;
        this.player2 = player2;
        this.movements = new ArrayList<Move>();
        this.winner = null;
        this.loser = null;
    }

    public Game(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<Move> getMovements() {
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
