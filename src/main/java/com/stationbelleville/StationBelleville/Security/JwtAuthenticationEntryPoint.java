package com.stationbelleville.StationBelleville.Security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.stationbelleville.StationBelleville.Exceptions.InvalidLoginResponse;

//an interface that provides implementation for method called commands
//called whenever an exception is thrown because a user is trying to
//access a resource blocked by authentication
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse httpServletResponse,
			AuthenticationException authException) throws IOException, ServletException {

		InvalidLoginResponse loginResponse = new InvalidLoginResponse();
		// convert to Json obj
		String jsonLoginResponse = new Gson().toJson(loginResponse);
		httpServletResponse.setContentType("application/json");
		httpServletResponse.setStatus(401);
		httpServletResponse.getWriter().print(jsonLoginResponse);
	}

}
