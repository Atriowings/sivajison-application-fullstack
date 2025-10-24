package com.TechGhuru.SivajiAndSons.service;


import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private String secretkey = "";
	
	public JwtService() {
		try {
			KeyGenerator KeyGen = KeyGenerator.getInstance("HmacSHA256");
			SecretKey key = KeyGen.generateKey();
			secretkey = Base64.getEncoder().encodeToString(key.getEncoded());
			
		}catch (Exception e) {
			System.out.println(e);
		}
	}

	public String generateToken(String username) {
		
		Map<String, Object> claims = new HashMap<>();
		
		return Jwts.builder()
				.claims()
				.add(claims)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 *1000))
				.and()
				.signWith(getkey())
				.compact();
				
				

	}
	
	private SecretKey getkey() {
		byte[] keybyts = Decoders.BASE64.decode(secretkey);
		return Keys.hmacShaKeyFor(keybyts);
	}

	public String extractUserName(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token,Claims::getSubject);
	}

	private <T> T extractClaim(String token,Function<Claims,T> clainResolver) {
		final Claims claim = extractAllClaims(token);
		return clainResolver.apply(claim);
		
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(getkey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
	public boolean validateToken(String token, UserDetails userdetails) {
		// TODO Auto-generated method stub
		final String userName = extractUserName(token);
		System.out.println(userName.equals(userdetails.getUsername()) && isTokenExpired(token));
		return (userName.equals(userdetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractExpiration(token).before(new Date());
	}
	
	private Date extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration);
	}
}
