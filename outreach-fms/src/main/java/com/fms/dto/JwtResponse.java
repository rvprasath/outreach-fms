package com.fms.dto;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final int statusCode;
	private final String status;
	private UserDto userDto;

	public JwtResponse(String jwttoken, String status, int statusCode, UserDto userDto) {
		this.jwttoken = jwttoken;
		this.status = status;
		this.statusCode = statusCode;
		this.userDto = userDto;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public String getJwttoken() {
		return jwttoken;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getStatus() {
		return status;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	
	
}