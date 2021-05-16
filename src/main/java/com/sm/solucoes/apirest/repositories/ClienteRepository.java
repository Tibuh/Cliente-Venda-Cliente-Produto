package com.sm.solucoes.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sm.solucoes.apirest.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Modifying
	@Query("UPDATE Cliente SET nome = :nome, cpfCnpj = :cpf WHERE id = :id")
	public void updateCliente(@Param("nome") String nome, @Param("cpf") String cpf, @Param("id") long id);

}
