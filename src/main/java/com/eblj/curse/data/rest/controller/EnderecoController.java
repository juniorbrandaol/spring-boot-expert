package com.eblj.curse.data.rest.controller;

import com.eblj.curse.data.domain.entities.Endereco;
import com.eblj.curse.data.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;
   public EnderecoController(EnderecoService enderecoService){
       this.enderecoService = enderecoService;
   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public Endereco save( @RequestBody Endereco endereco){
       return  enderecoService.salvar(endereco);
   }

   @GetMapping("{codigoEndereco}" )
   public Endereco getById( @PathVariable("codigoEndereco") Integer id) {
       return enderecoService.getById(id);
   }

    @GetMapping()
    public List<Endereco> findAll() {
        return enderecoService.findAll();
    }

    @DeleteMapping("{codigoEndereco}" )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable("codigoEndereco") Integer id){
       enderecoService.delete(id);
    }

    @PutMapping("{codigoEndereco}" )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("codigoEndereco") Integer id, @RequestBody Endereco endereco){
        enderecoService.update(id,endereco);
    }
}
