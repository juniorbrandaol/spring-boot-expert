package com.eblj.curse.data.service;

import com.eblj.curse.data.domain.entities.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioService {
    Usuario salvar(Usuario usuario);
    UserDetails autenticar(Usuario usuario);
}
