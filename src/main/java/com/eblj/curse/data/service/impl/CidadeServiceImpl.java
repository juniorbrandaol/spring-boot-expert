package com.eblj.curse.data.service.impl;

import com.eblj.curse.data.domain.entities.Cidade;
import com.eblj.curse.data.domain.repository.CidadeRepository;
import com.eblj.curse.data.exception.RegraNegocioException;
import com.eblj.curse.data.service.CidadeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CidadeServiceImpl implements CidadeService {

    private CidadeRepository repository;

    public CidadeServiceImpl(CidadeRepository repository){
        this.repository = repository;
    }

    @Override
    public Cidade save(Cidade cidade) {
        return repository.save(cidade);
    }

    @Override
    public Cidade findById(Integer id) {
        return repository.findById(id).
               orElseThrow( ()-> new RegraNegocioException("Cidade não encontrada"));
    }

    @Override
    public List<Cidade> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Integer id) {
        repository.findById(id).map( cidade->{
            repository.delete(cidade);
            return cidade;
        }).orElseThrow(()-> new RegraNegocioException("cidade não encontrada"));
    }

    @Override
    public void update(Integer id, Cidade cidade) {
       repository.findById(id).map( newCidade->{
           newCidade.setNome(cidade.getNome());
           newCidade.setUf(cidade.getUf());
           newCidade.setCodigoIBGE(cidade.getCodigoIBGE());
           repository.save(newCidade);
           return newCidade;
       }).orElseThrow(()-> new RegraNegocioException("Cidade não encontrada"));
    }
}
