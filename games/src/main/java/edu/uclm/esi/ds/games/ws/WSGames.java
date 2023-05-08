package edu.uclm.esi.ds.games.ws;

import java.io.IOException;
import java.util.ArrayList;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import edu.uclm.esi.ds.games.entities.Game;
import edu.uclm.esi.ds.games.entities.Move;
import edu.uclm.esi.ds.games.entities.User;
import edu.uclm.esi.ds.games.services.GamesService;
import edu.uclm.esi.ds.games.services.MoveService;
import edu.uclm.esi.ds.games.services.UsersService;

@Component
public class WSGames extends TextWebSocketHandler {
	private ArrayList<WebSocketSession> sessions = new ArrayList<>();

	private static GamesService gameService;

	private static UsersService userService;

	private static MoveService moveService;


	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		this.sessions.add(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		String payload = message.getPayload();
		JSONObject jso = new JSONObject(payload);
		String type = jso.getString("type");
		
		//tipos de mensajes que pueden haber (de tipo movimiento, chat, broadcast)
		if(type.equals("MOVEMENT")) { //tendrán el idMatch y el movement
			this.move(jso);
			
		}else if (type.equals("CHAT")){ //tendrán el target (a quien van) y el message
			this.chat(jso);
			
		}else if(type.equals("BROADCAST")) { // tendrán el message
			this.broadcast(jso);
			
		}else {
			this.send(session,"type","ERROR","message","Mensaje no reconocido");
		}
	}

	private void send(WebSocketSession session, String... tv) { //tv de tipos y valores
		
		JSONObject jso = new JSONObject();
		
		for (int i=0; i<tv.length; i=i+2) {
			jso.put(tv[i], tv[i+1]);
		}
		
		TextMessage message = new TextMessage(jso.toString());
		
		try { //por si se produce un error, se quita la sesion
			session.sendMessage(message);
		} catch (IOException e) {
			this.sessions.remove(session);
		}
		
	}

	private void chat(JSONObject jso) {
		// TODO Auto-generated method stub
		String target = jso.getString("target");
    	String message = jso.getString("message");

    // PRUEBA FUNCIONAMIENTO

    	for (WebSocketSession session : this.sessions) {
        	this.send(session, "type", "CHAT_RESULT", "message", "Mensaje enviado a " + target + ": " + message);
    	}
	}

	private void move(JSONObject jso) {

		String idMatch = jso.getString("idMatch");
		int col1 = jso.getJSONObject("move").getInt("col1");
		int row1 = jso.getJSONObject("move").getInt("row1");
		int col2 = jso.getJSONObject("move").getInt("col2");
		int row2 = jso.getJSONObject("move").getInt("row2");
		Long idMove = jso.getJSONObject("move").getLong("id");
		String userName = jso.getJSONObject("move").getString("user");

		Game game = gameService.findGame(idMatch);
		User user = userService.findUser(userName);

		Move move= new Move(idMove, game, user, row1, col1, row2, col2);

		moveService.guardar(move);


    	for (WebSocketSession session : this.sessions) {
        	this.send(session, "type", "MOVEMENT_RESULT", "message", "Movimiento procesado: " + move);
    	}

	}
	

	private void broadcast(JSONObject jso) {
		
		TextMessage message = new TextMessage(jso.getString("message")); 

		for (WebSocketSession client: this.sessions) {
			Runnable r  = new Runnable() { 
				@Override
				public void run() {
					try {
						client.sendMessage(message);
					} catch (IOException e) {
						WSGames.this.sessions.remove(client); 
					}
				}
			
			};
			
			new Thread(r).start(); 
		}
	}

	@Autowired
	public void setGamesService(GamesService gameService) {
		WSGames.gameService = gameService;
	}

	@Autowired
	public void setMoveService(MoveService moveService) {
		WSGames.moveService = moveService;
	}

	@Autowired
	public void setUsersService(UsersService userService) {
		WSGames.userService = userService;
	}

	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
	}
	
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		this.sessions.remove(session);
		
		// JSONObject jso = new JSONObject();
		// jso.put("type", "BYE");
		// jso.put("message", "Un usuario se ha ido");
	
		// this.broadcast(jso);
	}
	
}