package com.stationbelleville.StationBelleville.Security;

import static com.stationbelleville.StationBelleville.Security.SecurityPit.EXPIRE_TIME;
import static com.stationbelleville.StationBelleville.Security.SecurityPit.SECRET;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.stationbelleville.StationBelleville.Domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTTokenProvider {

	// generate token
	public String generateToken(Authentication auth) {
		// start building token
		User user = (User) auth.getPrincipal();
		Date now = new Date(System.currentTimeMillis());

		Date expire = new Date(now.getTime() + EXPIRE_TIME);

		// get Id so token can store it
		String userId = Long.toString(user.getId());

		// setClaims takes Map as args
		// token info holder
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", Long.toString(user.getId()));
		claims.put("email", user.getEmail());
		claims.put("firstName", user.getFirstName());
		claims.put("isAdmin", user.getIsAdmin());

		// claims is info about user, generate token
		return Jwts.builder().setSubject(userId).setClaims(claims).setIssuedAt(now).setExpiration(expire)
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
	}

	// validate JWT token
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
			return true;
		} catch (SignatureException se) {
			System.out.println("Invalid JWT Signature");
		} catch (MalformedJwtException mje) {
			System.out.println("Invalid JWT Token");
		} catch (ExpiredJwtException eje) {
			System.out.println("Expired JWT Signature");
		} catch (UnsupportedJwtException uje) {
			System.out.println("Unsupported JWT Token");
		} catch (IllegalArgumentException iee) {
			System.out.println("JWT Claims String is Empty");
		}

		return false;
	}

	// get user id from token to set up filters
	public Long getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
		String id = (String) claims.get("id");
		return Long.parseLong(id);
	}
}
