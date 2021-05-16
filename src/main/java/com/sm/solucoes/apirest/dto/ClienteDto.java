package com.sm.solucoes.apirest.dto;

import java.io.Serializable;

import com.sm.solucoes.apirest.entities.Cliente;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class ClienteDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Setter
	private Long id;

	@Setter
	private String nome;

	@Setter
	private String cpfCnpj;

	public ClienteDto(Long id, String nome, String cpfCnpj) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
	}

	public ClienteDto(Cliente cliente) {
		id = cliente.getId();
		nome = cliente.getNome();
		cpfCnpj = cliente.getCpfCnpj();
	}
}
