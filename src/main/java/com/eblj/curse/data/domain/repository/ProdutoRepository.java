package com.eblj.curse.data.domain.repository;

import com.eblj.curse.data.domain.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Integer> {
}
