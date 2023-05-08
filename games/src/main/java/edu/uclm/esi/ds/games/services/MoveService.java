package edu.uclm.esi.ds.games.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uclm.esi.ds.games.dao.MoveDAO;
import edu.uclm.esi.ds.games.entities.Move;

@Service
public class MoveService {
    
    @Autowired
    private MoveDAO moveDAO;

    public void guardar(Move move){
		  moveDAO.save(move);
	  }

    
}
