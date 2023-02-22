package com.eblj.curse.data.service.impl;

import com.eblj.curse.data.domain.entities.Usuario;
import com.eblj.curse.data.domain.repository.UsuarioRepository;
import com.eblj.curse.data.exception.SenhaInvalidaException;
import com.eblj.curse.data.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
carregar o usuario da base de dados através do seu login
 */
@Service
public class  UsuarioServiceImpl implements UserDetailsService , UsuarioService {  //interface do spring security
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Usuario usuario =  usuarioRepository.findByLogin(username)
                .orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado na base de dados"));

        String[] roles = usuario.isAdmin() ?
                new String[]{"ADMIN","USER"}: new String[]{"USER"};

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }

    @Transactional
    public Usuario salvar(Usuario usuario){
       return usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails autenticar(Usuario usuario) {
        UserDetails user= loadUserByUsername(usuario.getLogin());
        boolean seSenhas = encoder.matches(usuario.getSenha(),user.getPassword());
        if(seSenhas){
            return user;
        }
        throw new SenhaInvalidaException();
    }
}
