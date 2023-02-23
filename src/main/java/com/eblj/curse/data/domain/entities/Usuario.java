package com.eblj.curse.data.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column(name = "login",nullable = false)
   @NotEmpty(message = "O campo login é obrigatório.")
   @Length(min = 4, max = 12, message = "O login deverá ter no mínimo {min} e no máximo {max} caracteres")
   private String login;
   @Column(name = "senha",nullable = false)
   @NotEmpty(message = "O campo senha é obrigatório.")
   private String senha;
   @Column
   private boolean admin;
    public Usuario(){}

   public Usuario(Integer id, String login, String senha) {
      this.id = id;
      this.login = login;
      this.senha = senha;

   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getLogin() {
      return login;
   }

   public void setLogin(String login) {
      this.login = login;
   }

   public String getSenha() {
      return senha;
   }

   public void setSenha(String senha) {
      this.senha = senha;
   }

   public boolean isAdmin() {
      return admin;
   }

   public void setAdmin(boolean admin) {
      this.admin = admin;
   }

   @Override
   public String toString() {
      return "Usuario{" +
              "id=" + id +
              ", login='" + login + '\'' +
              ", senha='" + senha + '\'' +
              ", role='" + admin + '\'' +
              '}';
   }
}
