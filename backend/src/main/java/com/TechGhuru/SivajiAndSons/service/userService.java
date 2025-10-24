package com.TechGhuru.SivajiAndSons.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.TechGhuru.SivajiAndSons.dto.LoggedInUser;
import com.TechGhuru.SivajiAndSons.model.users;
import com.TechGhuru.SivajiAndSons.repository.Usersrepo;

@Service
public class userService {

	@Autowired
	private Usersrepo repo;
	@Autowired
	private AuthenticationManager authmanager;
	@Autowired
	private JwtService jwtservice;
	@Autowired
	private LoggedInUser loguser;
	
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
	
	public users register(users user) {
		if(user.getRole() == null) {
			user.setRole("staff");
		}else if(user.getRole().equals("admin"))
			user.setRole("admin");
		else {
			user.setRole("staff");
		}
//		String number = user.getNumber();
//		String name =user.getUsername();
//		user.setName(name);
//		user.setUsername(number);
//		System.out.println("this is password :: " + user.getPassword());
		user.setPassword(encoder.encode(user.getPassword()));
		return repo.save(user);
	}
	
	
	public LoggedInUser verify(users user) {
		
		Authentication authentication = 
				authmanager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
	
		if(authentication.isAuthenticated()) {
			users loginuser =repo.findByUsername(user.getUsername());
			System.out.println( loginuser.getEmpid());
//					return jwtservice.generateToken(user.getUsername());
//					+user.getUsername()
//					+user.getEmpid();
			loguser.setId(loginuser.getId());
			loguser.setEmpid(loginuser.getEmpid());
			loguser.setName(loginuser.getName());
			loguser.setNumber(loginuser.getNumber());
			loguser.setRole(loginuser.getRole());
			loguser.setName(loginuser.getName());
			loguser.setUsername(loginuser.getUsername());
			loguser.setToken( jwtservice.generateToken(user.getUsername()));
			return loguser;
			
		};
		return loguser;
	
	
	}


	 
}
