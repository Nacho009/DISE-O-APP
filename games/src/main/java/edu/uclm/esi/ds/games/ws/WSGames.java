package edu.uclm.esi.ds.games.ws;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WSGames extends TextWebSocketHandler {
	private ArrayList<WebSocketSession> sessions = new ArrayList<>();
	
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
		
	}

	private void move(JSONObject jso) {
		// TODO Auto-generated method stub
		
	}

	private void broadcast(JSONObject jso) {
		
		TextMessage message = new TextMessage(jso.getString("message")); //Para extraer el mensaje que me llega
		
		for (WebSocketSession client: this.sessions) {
			Runnable r  = new Runnable() { //lo hacemos asi para hacer hilos para los msjs
				@Override
				public void run() {
					try {
						client.sendMessage(message);
					} catch (IOException e) {
						WSGames.this.sessions.remove(client); //pq aqui el this dentro de esto es el runnable
					}
				}
			
			};
			
			new Thread(r).start(); //esto lo hacemos para lanzar el hilo
		}
	}

	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
	}
	
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		this.sessions.remove(session);
		
		JSONObject jso = new JSONObject();
		jso.put("type", "BYE");
		jso.put("message", "Un usuario se ha ido");
	
		this.broadcast(jso);
	}
	
}