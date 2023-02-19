package com.eblj.curse.data.rest.controller;

import com.eblj.curse.data.domain.entities.Cidade;
import com.eblj.curse.data.service.CidadeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/cidade")
public class CidadeController {

    private CidadeService service;
    public CidadeController(CidadeService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade save( @RequestBody Cidade cidade){
       return service.save(cidade);
    }

    @GetMapping("{id}")
    public Cidade findById( @PathVariable Integer id){
        return service.findById(id);
    }

    @GetMapping
    public List<Cidade> findAll(){
        return service.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id ){
        service.delete(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id,@RequestBody Cidade cidade){
        service.update(id,cidade);
    }

}
