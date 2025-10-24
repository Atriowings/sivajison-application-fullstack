package com.TechGhuru.SivajiAndSons.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.TechGhuru.SivajiAndSons.model.users;

public class UserPriciple implements UserDetails {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private users user;
	
	
	public UserPriciple(users user) {
		super();
		this.user = user; 
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername(); 
	}

}
