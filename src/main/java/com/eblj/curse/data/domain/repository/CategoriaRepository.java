package com.eblj.curse.data.domain.repository;

import com.eblj.curse.data.domain.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
}
