package com.stationbelleville.StationBelleville.Security;

import static com.stationbelleville.StationBelleville.Security.SecurityPit.HEADER;
import static com.stationbelleville.StationBelleville.Security.SecurityPit.TOKEN_PREFIX;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.stationbelleville.StationBelleville.Domain.User;
import com.stationbelleville.StationBelleville.Services.CustomUserDetailsService;

//OncePerRequestFilter Filter base class that guarantees to be 
//just executed once per request, on any servlet container
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JWTTokenProvider jwtToken;

	@Autowired
	private CustomUserDetailsService cuds;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			// grab web token from our request
			String jwt = getJWTFromRequest(request);

			if (StringUtils.hasText(jwt) && jwtToken.validateToken(jwt)) {
				Long userId = jwtToken.getUserIdFromJWT(jwt);
				User userDetails = cuds.loadUserById(userId);

				// remove Collections.emptyList for roles
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null,
						Collections.emptyList());

				//
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (Exception ex) {
			logger.error("Could not set user authentication in security ", ex);
		}

		filterChain.doFilter(request, response);
	}

	private String getJWTFromRequest(HttpServletRequest request) {
		String token = request.getHeader(HEADER);

		if (StringUtils.hasText(token) && token.startsWith(TOKEN_PREFIX)) {
			// return token starting at position 7, removing 'Bearer '
			return token.substring(7, token.length());
		}

		return null;
	}

}
