package com.red.social.proyecto.app.security;

public class JwtResponse {

    public JwtResponse(){}
    public JwtResponse(String token){
        this.token=token;
    }

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
