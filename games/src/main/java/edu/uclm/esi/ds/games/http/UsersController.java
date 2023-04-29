package edu.uclm.esi.ds.games.http;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.uclm.esi.ds.games.domain.Match;
import edu.uclm.esi.ds.games.services.GamesService;
import edu.uclm.esi.ds.games.services.UsersService;

@RestController
@CrossOrigin(origins =  {"http://localhost:4200", "http://localhost:60225"})
@RequestMapping("users")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@PostMapping("/register") //con games y esto nuestra url sera: http:/-----/games/solicitarPartida
	public ResponseEntity<Object> register(@RequestBody Map<String, Object> info) { //Request param es para que sea un parametro de la url
		
		String name = info.get("name").toString();
		String email = info.get("email").toString();
		String pwd1 = info.get("pwd1").toString();
		String pwd2 = info.get("pwd2").toString();
		
		if (!pwd1.equals(pwd2))
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Las contraseñas no coinciden");
		
		try {
			this.usersService.register(name,email,pwd1);
			return ResponseEntity.ok().body("{\"message\": \"Registro exitoso\"}");

		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}


	}
	
	@PutMapping("/login")
	public ResponseEntity<Object> login(@RequestBody Map<String, Object> info) {
	  String name = info.get("name").toString();
	  String pwd = info.get("pwd").toString();
	  
	  try {
		this.usersService.login(name, pwd);
		return ResponseEntity.ok().body("{\"message\": \"Inicio de sesión exitoso\"}");
	  } catch (Exception e) {
		throw new ResponseStatusException(HttpStatus.FORBIDDEN);
	  }
	}
	
	

}
//en el back_end vamos a tener:
//controladores del back_end, estos .java como gamesController
//para los controller tendremos otros .java para los service
//y luego esos service se conectaran a unos .java que van a ser los dao que van a conectar con la base de datos
//para manejar esos dao vamos a tener unas clases propias para manejar cada uno de los dao, y esas clases tienen 
//que tener una referencia en la tabla de la base de datos(a lo mejor tenemos que modificar el nombre porque sea una
//palabra que ya esté reservada
