package com.stationbelleville.StationBelleville.Payload;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

	@NotBlank(message = "Username cannot be blank")
	private String email;
	@NotBlank(message = "Password cannot be blank")
	private String password;

}
