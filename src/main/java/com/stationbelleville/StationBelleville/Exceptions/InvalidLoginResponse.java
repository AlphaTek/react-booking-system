package com.stationbelleville.StationBelleville.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvalidLoginResponse {

	private String email;
	private String password;

	public InvalidLoginResponse() {
		this.email = "Invalid Username";
		this.password = "Invalid Password";
	}
}
