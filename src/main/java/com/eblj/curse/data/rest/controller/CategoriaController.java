package com.eblj.curse.data.rest.controller;

import com.eblj.curse.data.domain.entities.Categoria;
import com.eblj.curse.data.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    public CategoriaController(CategoriaService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria save( @RequestBody Categoria categoria){
       return service.salvar(categoria);
    }

    @GetMapping("{id}")
    public Categoria findById(@PathVariable Integer id){
        return service.findById(id);
    }

    @GetMapping
    public List<Categoria> findAll(){
        return service.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id){
        service.delete(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id ,@RequestBody Categoria categoria){
        service.update(id,categoria);
    }

}
