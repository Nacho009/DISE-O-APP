package edu.uclm.esi.ds.games.services;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
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

	public List<List<Integer>> requestBoard(){
		return convertToIntegerList(this.matches.get("nm").getBoards().get(0).getDigits());
	}

	public List<List<Integer>> convertToIntegerList(byte[][] byteArray) {
		List<List<Integer>> integerList = new ArrayList<>();
		for (byte[] row : byteArray) {
			List<Integer> rowList = new ArrayList<>();
			for (byte b : row) {
				rowList.add((int) b & 0xFF);
			}
			integerList.add(rowList);
		}
		return integerList;
	}

}
