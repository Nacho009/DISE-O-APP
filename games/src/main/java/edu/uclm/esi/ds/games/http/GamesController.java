package edu.uclm.esi.ds.games.http;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.uclm.esi.ds.games.domain.Board;
import edu.uclm.esi.ds.games.domain.Match;
import edu.uclm.esi.ds.games.services.GamesService;

@RestController
@CrossOrigin(origins =  {"*"})
@RequestMapping("games")
public class GamesController {
	
	@Autowired
	private GamesService gamesService;
	
	@GetMapping("/requestGame") 
	public Match requestGame(@RequestParam String juego, @RequestParam String player) { 
		
		if (!juego.equals("nm"))
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encuentra ese juego");
		
		return this.gamesService.requestGame(juego, player);
		
	}

	@GetMapping("/requestBoard") 
	public List<List<Integer>> requestBoard(@RequestParam String player) { 
		return this.gamesService.requestBoard(player);
	}

	@GetMapping("/requestDigits") 
	public List<List<Integer>> requestDigits(@RequestParam String player) { 
		return this.gamesService.añadirDigitos(player);
	}

}

