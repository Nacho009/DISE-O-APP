package edu.uclm.esi.ds.games.domain;

import java.security.SecureRandom;

public class Board {
	
	private byte[][] digits;
	
	public Board() {
		
		SecureRandom dado = new SecureRandom();
		
		this.digits = new byte[9][9];
		
		for(int i=0; i<3; i++) {
			
			for(int j=0; i<9; j++) {
				
				this.digits[i][j] = (byte) dado.nextInt(1,10); //hacemos cast a byte porque genera enteros
				
			}
		}
				
	}

	public Board copy() { //metodo para crear una copia del tablero

		Board result = new Board();
		
		for(int i=0; i<3; i++) {
			
			for(int j=0; i<9; j++) {
				
				result.digits[i][j] = this.digits[i][j]; //hacemos cast a byte porque genera enteros
				
			}
		}
		
		return result;
	}
	
	public byte[][] getDigits(){
		
		return digits;
		
	}

}
