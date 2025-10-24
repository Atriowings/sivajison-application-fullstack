package com.TechGhuru.SivajiAndSons.security;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.TechGhuru.SivajiAndSons.service.JwtService;
import com.TechGhuru.SivajiAndSons.service.MyUserDetailService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtService jwtservice;
	@Autowired
	private ApplicationContext context;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
//		response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Origin,Content-Type, Accept, x-device-user-agent, Content-Type");
		// TODO Auto-generated method stub
		
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String username = null;
		
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7) ;
			username = jwtservice.extractUserName(token);
			System.out.println(token);
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userdetails = context.getBean(MyUserDetailService.class).loadUserByUsername(username);
			
			if(jwtservice.validateToken(token,userdetails)) {
				UsernamePasswordAuthenticationToken authtoken =
						new UsernamePasswordAuthenticationToken(userdetails, null,userdetails.getAuthorities());
			authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authtoken);
			
			}
			 
		}
		filterChain.doFilter(request, response);
	}

	
}
