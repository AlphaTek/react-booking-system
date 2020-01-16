package com.stationbelleville.StationBelleville.Security;

public class SecurityPit {

	public static final String REGISTER_API = "/api/users/**";
	public static final String SECRET = "UnknownToGenerateJWTs";
	// space REQUIRED in Bearer
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER = "Authorization";

	public static final long EXPIRE_TIME = 600000000;

}
