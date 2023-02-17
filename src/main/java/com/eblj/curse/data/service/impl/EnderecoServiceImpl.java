package com.eblj.curse.data.service.impl;

import com.eblj.curse.data.domain.entities.Endereco;
import com.eblj.curse.data.domain.repository.EnderecoRepository;
import com.eblj.curse.data.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@Transactional
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    public EnderecoServiceImpl(EnderecoRepository repository){
        this.repository = repository;
    }

    @Override
    public Endereco salvar(Endereco endereco) {
        return repository.save((endereco));
    }

    @Override
    public Endereco getById(Integer id) {
        return repository.findById(id).
                orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Endereço não encontrado"));
    }

    @Override
    public List<Endereco> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Integer id) {
         repository.findById(id).map( endereco ->{
             repository.delete((endereco));
             return Void.TYPE;
         }).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Endereço não encontrado"));
    }

    @Override
    public List<Endereco> find(Endereco filtro) {
        return null;
    }

    @Override
    public void update(Integer id, Endereco endereco) {
         repository.findById(id).map( e ->{
             e.setRua(endereco.getRua());
             e.setNumero(endereco.getNumero());
             e.setBairro(endereco.getBairro());
             e.setCep(endereco.getCep());
             e.setCidade(endereco.getCidade());
             e.setUf(endereco.getUf());
             repository.save(e);
             return e;
         }).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Endereço não encontrado"));
    }
}
