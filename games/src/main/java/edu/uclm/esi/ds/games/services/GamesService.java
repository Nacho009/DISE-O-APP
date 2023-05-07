package edu.uclm.esi.ds.games.services;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import edu.uclm.esi.ds.games.dao.GameDAO;
import edu.uclm.esi.ds.games.dao.UserDAO;
import edu.uclm.esi.ds.games.domain.Match;
import edu.uclm.esi.ds.games.domain.WaitingRoom;
import edu.uclm.esi.ds.games.entities.Game;
import edu.uclm.esi.ds.games.entities.User;

@Service
public class GamesService {

	private WaitingRoom waitingRoom;
	private ConcurrentHashMap<String, Match> matches;

	@Autowired
	private UserDAO userRepository;

	@Autowired
	private GameDAO gameRepository;


	public GamesService() {
		
		this.waitingRoom = new WaitingRoom();
		this.matches = new ConcurrentHashMap<>();
		
	}
	
	public Match requestGame(String juego, String player) {
		
		Match match = this.waitingRoom.findMatch(juego,player);
		
		if (match.isReady()){
			this.matches.put(juego, match);
			User user1 = userRepository.findByName(match.getPlayers().get(0));
			User user2 = userRepository.findByName(match.getPlayers().get(1));

			Game game=new Game(match.getId(), user1, user2);

			gameRepository.save(game);
		}

		
		return match;
	}

	public List<List<Integer>> requestBoard(String player){
		int pos = this.matches.get("nm").getPlayers().indexOf(player);
		return convertToIntegerList(this.matches.get("nm").getBoards().get(pos).getDigits());
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
	
	public Game findGame(String id) {
		return gameRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Game not found with id: " + id));
	}
	

}
