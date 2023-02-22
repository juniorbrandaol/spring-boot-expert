package com.eblj.curse.data.rest.dto;

public class TokenDTO {

    private String login;
    private String token;
    public TokenDTO(){}

    public TokenDTO(String login, String token) {
        this.login = login;
        this.token = token;
    }

    public String getLogin() {
        return login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
