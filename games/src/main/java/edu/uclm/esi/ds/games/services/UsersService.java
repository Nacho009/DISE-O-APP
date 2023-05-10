package edu.uclm.esi.ds.games.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import edu.uclm.esi.ds.games.dao.UserDAO;
import edu.uclm.esi.ds.games.entities.User;

@Service
public class UsersService {
	
	@Autowired
	private UserDAO userDAO;
	
	public void register(String name, String email, String pwd) {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPwd(pwd);
		
		User aux = this.userDAO.findByName(name);
		
		if(aux != null)
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Ya existe este usuario");
		else{
			this.userDAO.save(user);
		}
	}
	
	public void login(String name, String pwd){
		
		User user = this.userDAO.findByName(name);
		
		if(user == null)
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Credenciales Inválidas");
		
		String pwdEncripted = org.apache.commons.codec.digest.DigestUtils.sha512Hex(pwd);
		if(!user.getPwd().equals(pwdEncripted))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Credenciales Inválidas");
	}


	public User findUser(String name){
		return userDAO.findByName(name);

	}
}
