package com.TechGhuru.SivajiAndSons.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.TechGhuru.SivajiAndSons.model.users;
import com.TechGhuru.SivajiAndSons.repository.Usersrepo;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private Usersrepo userrepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		
		users user = userrepo.findByUsername(username);
		
		if(user == null) {
			System.out.println("user not found ");
			throw new UsernameNotFoundException("Not found");
			
		}
		return new UserPriciple(user);
	} 
}




