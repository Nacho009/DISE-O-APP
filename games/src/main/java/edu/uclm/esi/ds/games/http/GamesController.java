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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("games")
public class GamesController {
	
	@Autowired
	private GamesService gamesService;
	
	@GetMapping("/requestGame") //con games y esto nuestra url sera: http:/-----/games/solicitarPartida
	public Match requestGame(@RequestParam String juego, @RequestParam String player) { //Request param es para que sea un parametro de la url
		
		if (!juego.equals("nm"))
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encuentra ese juego");
		
		return this.gamesService.requestGame(juego, player);
		
	}

	@GetMapping("/requestBoard") 
	public List<List<Integer>> requestBoard() { 
		return this.gamesService.requestBoard();
	}

}
//en el back_end vamos a tener:
//controladores del back_end, estos .java como gamesController
//para los controller tendremos otros .java para los service
//y luego esos service se conectaran a unos .java que van a ser los dao que van a conectar con la base de datos
//para manejar esos dao vamos a tener unas clases propias para manejar cada uno de los dao, y esas clases tienen 
//que tener una referencia en la tabla de la base de datos(a lo mejor tenemos que modificar el nombre porque sea una
//palabra que ya esté reservada
