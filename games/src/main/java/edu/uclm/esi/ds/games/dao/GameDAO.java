package edu.uclm.esi.ds.games.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uclm.esi.ds.games.entities.Game;

@Repository
public interface GameDAO extends JpaRepository<Game, String>{
	
	Optional<Game> findById(String id);

	
}
