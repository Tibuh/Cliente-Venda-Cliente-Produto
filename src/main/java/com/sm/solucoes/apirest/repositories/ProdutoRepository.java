package com.sm.solucoes.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sm.solucoes.apirest.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Modifying
	@Query("UPDATE Produto SET nome = :nome, valor = :valor WHERE id = :id")
	public void updateProduto(@Param("nome") String nome, @Param("valor") double valor, @Param("id") long id);

}
