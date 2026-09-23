package com.studentassistant.backend.dto;

public class LoginResponse {

    private String message;
    private String name;
    private String email;
    private String token;

    public LoginResponse() {
    }

    public LoginResponse(String message, String name, String email, String token) {
        this.message = message;
        this.name = name;
        this.email = email;
        this.token = token;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    
    
}