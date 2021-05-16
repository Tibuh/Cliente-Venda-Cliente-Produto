package com.sm.solucoes.apirest.dto;

import java.io.Serializable;

import com.sm.solucoes.apirest.entities.Produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private String nome;

	private double valor;

	public ProdutoDto(Produto produto) {
		id = produto.getId();
		nome = produto.getNome();
		valor = produto.getValor();
	}

}
