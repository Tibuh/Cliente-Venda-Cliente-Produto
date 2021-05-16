package com.sm.solucoes.apirest.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sm.solucoes.apirest.entities.Cliente;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@NoArgsConstructor
public class VendaDataEntregaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Setter
	private long id;

	@Setter
	private LocalDate data;

	@Setter String dataEntrega;
	
	@Setter
	private Cliente cliente;

	private List<ProdutoDto> produtos = new ArrayList<>();

	public VendaDataEntregaDto(long id, LocalDate data, String dataEntrega, Cliente cliente, List<ProdutoDto> produtos) {
		super();
		this.id = id;
		this.data = data;
		this.cliente = cliente;
		this.dataEntrega = dataEntrega;
		this.produtos = produtos;
	}

}
