package com.stationbelleville.StationBelleville.Web;

import static com.stationbelleville.StationBelleville.Security.SecurityPit.TOKEN_PREFIX;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stationbelleville.StationBelleville.Domain.User;
import com.stationbelleville.StationBelleville.Payload.JWTLoginResponse;
import com.stationbelleville.StationBelleville.Payload.LoginRequest;
import com.stationbelleville.StationBelleville.Security.JWTTokenProvider;
import com.stationbelleville.StationBelleville.Services.ErrorValidationService;
import com.stationbelleville.StationBelleville.Services.UserService;
import com.stationbelleville.StationBelleville.Validators.UserValidator;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private ErrorValidationService error;

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private JWTTokenProvider jwtToken;

	@Autowired
	private AuthenticationManager authManager;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest login, BindingResult result) {
		ResponseEntity<?> errorMap = error.ErrorValidationService(result);
		if (errorMap != null)
			return errorMap;

		Authentication auth = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwt = TOKEN_PREFIX + jwtToken.generateToken(auth);
		return ResponseEntity.ok(new JWTLoginResponse(true, jwt));
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {

		userValidator.validate(user, result);
		// validate passwords match
		ResponseEntity<?> errorMap = error.ErrorValidationService(result);
		if (errorMap != null)
			return errorMap;

		User newUser = userService.saveUser(user);

		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}
}
