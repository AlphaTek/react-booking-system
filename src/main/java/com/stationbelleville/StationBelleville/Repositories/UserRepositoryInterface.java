package com.stationbelleville.StationBelleville.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stationbelleville.StationBelleville.Domain.User;

@Repository
public interface UserRepositoryInterface extends CrudRepository<User, Long> {

	User findByEmail(String email);

	User getById(Long id);
}