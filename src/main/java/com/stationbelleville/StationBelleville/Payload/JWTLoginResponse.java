package com.stationbelleville.StationBelleville.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTLoginResponse {

	private boolean success;
	private String token;

}
