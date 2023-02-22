package com.eblj.curse.data.domain.repository;

import com.eblj.curse.data.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    Optional<Usuario> findByLogin(String login);
}
