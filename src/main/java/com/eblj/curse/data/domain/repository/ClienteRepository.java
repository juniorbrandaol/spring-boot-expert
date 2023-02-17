package com.eblj.curse.data.domain.repository;


import com.eblj.curse.data.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

    List<Cliente> findByNomeLike(String nome);

    /*outra forma de fazer queries(hql).Criando as minhas proprias*/
    @Query(value=" select c from Cliente c where c.nome like  :nome")
    List<Cliente> procurarPorNome(@Param("nome") String nome);

    List<Cliente> findByNomeLikeIgnoreCase(String nome);

    boolean existsByNomeIgnoreCase(String nome);

    @Query(" select c from Cliente c left join fetch c.pedidos where c.id= :id ")
    Cliente findClienteFetchPedido(@Param("id") Integer id);

}
