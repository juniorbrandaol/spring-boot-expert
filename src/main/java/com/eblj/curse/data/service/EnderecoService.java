package com.eblj.curse.data.service;

import com.eblj.curse.data.domain.entities.Endereco;

import java.util.List;

public interface EnderecoService {
    Endereco salvar(Endereco endereco);
    Endereco getById(Integer id);
    List<Endereco> findAll();
    void delete(Integer id);
    List<Endereco> find(Endereco filtro);
    void update(Integer id,Endereco endereco);
}
