package com.eblj.curse.data.service.impl;

import com.eblj.curse.data.domain.entities.Marca;
import com.eblj.curse.data.domain.repository.MarcaRepository;
import com.eblj.curse.data.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class MarcaServiceImpl implements MarcaService {
    @Autowired
    private MarcaRepository repository;

    public MarcaServiceImpl(MarcaRepository repository){
        this.repository = repository;
    }

    @Override
    public Marca salvar(Marca marca) {
        return repository.save(marca);
    }

    @Override
    public Marca getById(Integer id) {
        return  repository.findById(id).
                orElseThrow( ()->new ResponseStatusException (HttpStatus.NOT_FOUND,"Marca não encontrada"));
    }

    @Override
    public List<Marca> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Integer id) {
        repository.findById(id).map( marca ->{
            repository.delete(marca);
            return marca;
        }).orElseThrow(()->new ResponseStatusException (HttpStatus.NOT_FOUND,"Marca não encontrada"));
    }

    @Override
    public List<Marca> find(Marca filtro) {
        return null;
    }

    @Override
    public void update(Integer id, Marca marca) {

        repository.findById(id).map( newMarca ->{
            newMarca.setNome(marca.getNome());
            repository.save(newMarca);
            return  newMarca;
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Marca não encontrada"));
    }
}
