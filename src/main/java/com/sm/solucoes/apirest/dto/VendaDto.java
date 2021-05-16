package com.sm.solucoes.apirest.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sm.solucoes.apirest.entities.Cliente;
import com.sm.solucoes.apirest.entities.Venda;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class VendaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Setter
	private long id;

	@Setter
	private LocalDate data;

	@Setter
	private Cliente cliente;

	private List<ProdutoDto> produtos = new ArrayList<>();

	public VendaDto(long id, Cliente cliente) {
		super();
		this.id = id;
		this.cliente = cliente;
	}

	public VendaDto(Venda venda) {
		id = venda.getId();
		data = venda.getData();
		cliente = venda.getCliente();
		produtos = venda.getProdutos().stream().map(produto -> new ProdutoDto(produto)).collect(Collectors.toList());
	}

}
