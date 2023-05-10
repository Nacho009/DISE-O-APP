package edu.uclm.esi.ds.games.services;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import edu.uclm.esi.ds.games.dao.GameDAO;
import edu.uclm.esi.ds.games.dao.UserDAO;
import edu.uclm.esi.ds.games.domain.Match;
import edu.uclm.esi.ds.games.domain.WaitingRoom;
import edu.uclm.esi.ds.games.entities.Game;
import edu.uclm.esi.ds.games.entities.Move;
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
	
	public List<List<Integer>> updateMove(Move move){

		int pos = this.matches.get("nm").getPlayers().indexOf(move.getPlayer().getName());

		this.matches.get("nm").getBoards().get(pos).getDigits()[move.getRow1()][move.getCol1()]=0;
		this.matches.get("nm").getBoards().get(pos).getDigits()[move.getRow2()][move.getCol2()]=0;

		return requestBoard(move.getPlayer().getName());
	}

	public boolean esGanador(List<List<Integer>> tablero, String player){

		boolean ganador=false;
		int pos = this.matches.get("nm").getPlayers().indexOf(player);

		int cont=0;
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(this.matches.get("nm").getBoards().get(pos).getDigits()[i][j]==0){
					cont++;
				}
			}
		}
		if(cont==49){
			ganador=true;
		}
		return ganador;
	}
	public List<List<Integer>> añadirDigitos(String player){

		SecureRandom dado = new SecureRandom();
		int pos = this.matches.get("nm").getPlayers().indexOf(player);
		
		int generados=dado.nextInt(15,30);

		for(int i=0; i<9; i++) {
			
			for(int j=0; j<9; j++) {
				
				if(this.matches.get("nm").getBoards().get(pos).getDigits()[i][j] == 0 && generados !=0){
					generados--;
					this.matches.get("nm").getBoards().get(pos).getDigits()[i][j] = (byte) dado.nextInt(1,10);		
				}
			}
		}
		return requestBoard(player);
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
