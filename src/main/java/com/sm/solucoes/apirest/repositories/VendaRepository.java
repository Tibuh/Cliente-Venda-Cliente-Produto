package com.sm.solucoes.apirest.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sm.solucoes.apirest.entities.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {

	@Modifying
	@Query("UPDATE Venda SET data = :data, idCliente = :idCliente WHERE id = :id")
	public void updateProduto(@Param("data") LocalDate data, @Param("idCliente") long idCliente, @Param("id") long id);

}
