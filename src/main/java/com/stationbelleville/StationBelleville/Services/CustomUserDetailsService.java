package com.stationbelleville.StationBelleville.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stationbelleville.StationBelleville.Domain.User;
import com.stationbelleville.StationBelleville.Repositories.UserRepositoryInterface;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepositoryInterface userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username);

		if (user == null)
			new UsernameNotFoundException("Username does not exist");

		return user;
	}

	@Transactional
	public User loadUserById(Long id) {
		User user = userRepo.getById(id);

		if (user == null)
			new UsernameNotFoundException("Username does not exist");

		return user;
	}

}
