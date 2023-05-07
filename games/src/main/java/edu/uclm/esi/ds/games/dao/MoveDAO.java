package edu.uclm.esi.ds.games.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uclm.esi.ds.games.entities.Move;

@Repository
public interface MoveDAO extends JpaRepository<Move, Long>{
	
	Optional<Move> findById(Long id);

	
}
