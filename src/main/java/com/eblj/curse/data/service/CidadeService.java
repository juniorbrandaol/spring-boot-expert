package com.eblj.curse.data.service;

import com.eblj.curse.data.domain.entities.Cidade;

import java.util.List;

public interface CidadeService {

    Cidade save(Cidade cidade);
    Cidade findById(Integer id);
    List<Cidade> findAll();
    void delete(Integer id);
    void update(Integer id,Cidade cidade);

}
