package edu.uclm.esi.ds.games.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.uclm.esi.ds.games.entities.Game;

public interface GameDAO extends JpaRepository<Game, Long>{
	
	Optional<Game> findById(Long id);

	
}
