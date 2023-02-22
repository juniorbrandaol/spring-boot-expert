package com.eblj.curse.data.rest.dto;

public class CredenciaisDTO {
    private String login;
    private String senha;

    public CredenciaisDTO(){}

    public String getLogin() {
        return login;
    }


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
