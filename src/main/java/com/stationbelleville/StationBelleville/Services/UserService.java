package com.stationbelleville.StationBelleville.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.stationbelleville.StationBelleville.Domain.User;
import com.stationbelleville.StationBelleville.Exceptions.UserAlreadyExistsException;
import com.stationbelleville.StationBelleville.Repositories.UserRepositoryInterface;

@Service
public class UserService {

	@Autowired
	private UserRepositoryInterface userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User saveUser(User newUser) {

		newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

		try {
			// Username has to be unique(exception)
			newUser.setEmail(newUser.getEmail());
			// Make sure password and confirm password match

			// don't persist or show confirm password
			return userRepository.save(newUser);
		} catch (Exception ex) {
			throw new UserAlreadyExistsException("Username/ Email '" + newUser.getEmail() + "' already exists.");
		}

	}
}