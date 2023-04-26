package edu.uclm.esi.ds.games.services;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import edu.uclm.esi.ds.games.domain.Board;
import edu.uclm.esi.ds.games.domain.Match;
import edu.uclm.esi.ds.games.domain.WaitingRoom;

@Service

public class GamesService {

	private WaitingRoom waitingRoom;
	private ConcurrentHashMap<String, Match> matches;
	public GamesService() {
		
		this.waitingRoom = new WaitingRoom();
		this.matches = new ConcurrentHashMap<>();
		
	}
	
	public Match requestGame(String juego, String player) {
		
		Match match = this.waitingRoom.findMatch(juego,player);
		
		if (match.isReady())

			this.matches.put(juego, match);
		
		return match;
	}

	public Board requestBoard(){
		
		return this.matches.get("nm").getBoards().get(0);

	}

}
