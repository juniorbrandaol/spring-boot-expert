package com.eblj.curse.data.service.impl;

import com.eblj.curse.data.domain.entities.Fornecedor;
import com.eblj.curse.data.domain.repository.FornecedorRepository;
import com.eblj.curse.data.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class FornecedorServiceImpl implements FornecedorService {

    @Autowired
    private FornecedorRepository repository;

    public FornecedorServiceImpl(FornecedorRepository repository){
        this.repository = repository;
    }

    @Override
    public Fornecedor save(Fornecedor fornecedor) {
        return repository.save(fornecedor);
    }

    @Override
    public Fornecedor getFornecedorById(Integer id) {
        return repository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Fornecedor não encontrado"));
    }

    @Override
    public List<Fornecedor> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Integer id) {
        repository.findById(id).map(fornecedor -> {
            repository.delete(fornecedor);
            return fornecedor;
        }).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Fornecedor não encontrado"));

    }

    @Override
    public void update(Integer id, Fornecedor fornecedor) {
        repository.findById(id).map( newFornecedor->{
            newFornecedor.setNome(fornecedor.getNome());
            newFornecedor.setCnpj(fornecedor.getCnpj());
            newFornecedor.setTelefone(fornecedor.getTelefone());
            repository.save(newFornecedor);
            return  newFornecedor;
        }).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Fornecedor não encontrado"));


    }
}
