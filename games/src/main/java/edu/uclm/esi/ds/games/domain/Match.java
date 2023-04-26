package edu.uclm.esi.ds.games.domain;

import java.util.*;

public class Match {
	
	private String id;
	private boolean ready;
	private List<String> players;
	private HashMap<String, Board> boards;
	
	public Match(){
		
		this.id = UUID.randomUUID().toString();
		this.players = new ArrayList<>();
		this.boards = new HashMap<>();
		
	}
	
	public String getId() {
		return this.id;
	}

	public boolean isReady() {
		return this.ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}
	
	
	Board buildBoards() {

		Board board = new Board();
		this.boards.put(this.players.get(0), board);
		this.boards.put(this.players.get(1), board.copy());
		
		return board;
	}

	public void addPlayer(String player) {
		
		this.players.add(player);
		
		if (this.players.size()==2){
			this.ready=true;
			buildBoards();
			
		}

	}
	
	public List<String> getPlayers(){
		
		return this.players;
		
	}
	
	public List<Board> getBoards() {
		return boards.values().stream().toList();
	}

}
