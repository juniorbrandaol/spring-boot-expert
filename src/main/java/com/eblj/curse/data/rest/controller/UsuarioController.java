package com.eblj.curse.data.rest.controller;

import com.eblj.curse.data.domain.entities.Usuario;
import com.eblj.curse.data.exception.SenhaInvalidaException;
import com.eblj.curse.data.rest.dto.CredenciaisDTO;
import com.eblj.curse.data.rest.dto.TokenDTO;
import com.eblj.curse.data.security.jwt.JwtService;
import com.eblj.curse.data.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
   @Autowired
   private PasswordEncoder passwordEncoder;

   @Autowired
   private UsuarioService service;

   @Autowired
   private JwtService jwtService;

   public UsuarioController(){}

    public UsuarioController(UsuarioService service,PasswordEncoder passwordEncoder,JwtService jwtService) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
        this.jwtService= jwtService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar( @RequestBody @Valid Usuario usuario ){
       String senhaCripto = passwordEncoder.encode(usuario.getSenha());
       usuario.setSenha(senhaCripto);
       return service.salvar(usuario);
    }
    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){

      try{
        Usuario usuario = new Usuario();
          usuario.setLogin(credenciais.getLogin());
          usuario.setSenha(credenciais.getSenha());
        UserDetails usuarioAutenticado =  service.autenticar(usuario);
        String token =  jwtService.gerarToken(usuario);
        return  new TokenDTO(usuario.getLogin(),token);
      }
      catch (UsernameNotFoundException |SenhaInvalidaException  e){
        throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED ,e.getMessage());
      }
    }

}
