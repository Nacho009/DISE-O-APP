package edu.uclm.esi.ds.games.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uclm.esi.ds.games.entities.User;

@Repository
public interface UserDAO extends JpaRepository<User, String>{
	
	User findByName(String name);

	
}
