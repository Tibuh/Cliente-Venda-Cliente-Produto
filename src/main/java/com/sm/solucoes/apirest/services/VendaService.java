package com.sm.solucoes.apirest.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sm.solucoes.apirest.dto.ProdutoDto;
import com.sm.solucoes.apirest.dto.VendaDataEntregaDto;
import com.sm.solucoes.apirest.dto.VendaDto;
import com.sm.solucoes.apirest.entities.Produto;
import com.sm.solucoes.apirest.entities.Venda;
import com.sm.solucoes.apirest.repositories.ClienteRepository;
import com.sm.solucoes.apirest.repositories.ProdutoRepository;
import com.sm.solucoes.apirest.repositories.VendaRepository;
import com.sm.solucoes.apirest.services.excecao.ExcecaoNegocio;

@Service
public class VendaService {

	@Autowired
	ProdutoRepository produtoRepositorio;

	@Autowired
	VendaRepository vendaRepositorio;

	@Autowired
	ClienteRepository clienteRepositorio;

	/*
	 * Retorna todas vendas, é feito uma conversão do objeto que vem do banco para
	 * DTO. Possui um Read Only pois este serviço no acesso ao banco, só irá fazer
	 * uma leitura dos dados.
	 * 
	 * @return List<VendaDto>
	 */
	@Transactional(readOnly = true)
	public List<VendaDto> getAll() {
		return vendaRepositorio.findAll().stream().map(venda -> new VendaDto(venda)).collect(Collectors.toList());
	}

	/*
	 * Retorna todas as vendas com a data de entrega.
	 * 
	 * @return List<VendaDataEntregaDto>
	 */
	@Transactional(readOnly = true)
	public List<VendaDataEntregaDto> getVendasComDataEntrega() {
		List<VendaDto> vendaDto = vendaRepositorio.findAll().stream().map(venda -> new VendaDto(venda))
				.collect(Collectors.toList());
		return vendaDto.stream()
				.map(venda -> new VendaDataEntregaDto(venda.getId(), venda.getData(),
						venda.getData().plusDays(10).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
						venda.getCliente(), venda.getProdutos())

				).collect(Collectors.toList());
	}

	/*
	 * Retorna a venda com o Id passado
	 * 
	 * @return VendaDto
	 */
	@Transactional(readOnly = true)
	public VendaDto getById(long idVenda) {
		return new VendaDto(vendaRepositorio.findById(idVenda).get());
	}

	/*
	 * Recebe uma VendaDto passada, converte para a entidade do banco, tenta inserir
	 * e caso o metodo save retorne o objeto salvo, retorna uma string de sucesso,
	 * caso nao lanca Excecao
	 * 
	 * @Param VendaDto
	 * 
	 * @return String de sucesso
	 * 
	 * @Throw ExcecaoNegocio
	 */
	@Transactional
	public String insert(VendaDto vendaDto) throws ExcecaoNegocio {
		Venda venda = new Venda(vendaDto.getId(), LocalDate.now(), vendaDto.getCliente());

		for (ProdutoDto produtoAtual : vendaDto.getProdutos()) {
			Produto produtoBanco = produtoRepositorio.getOne(produtoAtual.getId());
			venda.getProdutos().add(produtoBanco);
		}

		if (vendaRepositorio.save(venda) != null) {
			return "Venda inserida com sucesso.";
		}

		throw new ExcecaoNegocio("Não foi possível cadastrar a venda.");
	}

	/*
	 * Recebe uma VendaDto passada, converte para a entidade do banco, tenta
	 * atualizar e caso de algum erro e lancada uma excecao
	 * 
	 * @Param VendaDto
	 * 
	 * @Throw ExcecaoNegocio
	 */
	@Transactional
	public void update(VendaDto vendaDto) throws ExcecaoNegocio {
		Venda venda = new Venda(vendaDto.getId(), vendaDto.getData(), vendaDto.getCliente());
		try {
			vendaRepositorio.updateProduto(venda.getData(), venda.getCliente().getId(), venda.getId());
		} catch (Exception e) {
			throw new ExcecaoNegocio("Erro ao atualizar venda.");
		}

	}

	/*
	 * Recebe o id da venda a ser excluida
	 * 
	 * 
	 * @Param idVenda
	 * 
	 * @Throw ExcecaoNegocio
	 */
	public void delete(long idVenda) throws ExcecaoNegocio {
		try {
			vendaRepositorio.deleteById(idVenda);
		} catch (Exception e) {
			throw new ExcecaoNegocio("Erro ao excluir venda.");
		}
	}
}
