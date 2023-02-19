package com.eblj.curse.data.service;

import com.eblj.curse.data.domain.entities.Cartao;

import java.util.List;

public interface CartaoService {

    Cartao save(Cartao cartao);
    Cartao findById(Integer id);
    List<Cartao> findAll();
    void delete(Integer id);
    void update(Integer id, Cartao cartao);
}
