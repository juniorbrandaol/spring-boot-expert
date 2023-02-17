package com.eblj.curse.data.service.impl;

import com.eblj.curse.data.domain.entities.Categoria;
import com.eblj.curse.data.domain.repository.CategoriaRepository;
import com.eblj.curse.data.exception.RegraNegocioException;
import com.eblj.curse.data.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

    private CategoriaRepository repository;

    public CategoriaServiceImpl(CategoriaRepository repository){
        this.repository = repository;
    }

    @Override
    public Categoria salvar(Categoria categoria) {
        return repository.save(categoria);
    }

    @Override
    public Categoria findById(Integer id) {
        return repository.findById(id).
               orElseThrow(()->new RegraNegocioException("Categoria não encontrada"));
    }

    @Override
    public List<Categoria> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Integer id) {
       repository.findById(id).map( categoria -> {
           repository.delete(categoria);
           return categoria;
       }).  orElseThrow(()->new RegraNegocioException("Categoria não encontrada"));
    }

    @Override
    public void update(Integer id, Categoria categoria) {
      repository.findById(id).map( newCategory ->{
          newCategory.setNome(categoria.getNome());
          newCategory.setImgPath(categoria.getImgPath());
          repository.save(newCategory);
          return  newCategory;
      }).  orElseThrow(()->new RegraNegocioException("Categoria não encontrada"));
    }
}
