package com.sm.solucoes.apirest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sm.solucoes.apirest.dto.ProdutoDto;
import com.sm.solucoes.apirest.entities.Produto;
import com.sm.solucoes.apirest.repositories.ProdutoRepository;
import com.sm.solucoes.apirest.services.excecao.DataIntegrityException;
import com.sm.solucoes.apirest.services.excecao.ExcecaoNegocio;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepositorio;

	/*
	 * Retorna todos os produtos, é feito uma conversão do objeto que vem do banco
	 * para DTO. Possui um Read Only pois este serviço no acesso ao banco, só irá
	 * fazer uma leitura dos dados.
	 * 
	 * @return List<ProdutoDto>
	 */
	@Transactional(readOnly = true)
	public List<ProdutoDto> getAll() {
		return produtoRepositorio.findAll().stream().map(produto -> new ProdutoDto(produto))
				.collect(Collectors.toList());
	}

	/*
	 * Retorna o produto com o Id passado
	 * 
	 * @return ProdutoDto
	 */
	@Transactional(readOnly = true)
	public ProdutoDto getById(long idProduto) {
		return new ProdutoDto(produtoRepositorio.findById(idProduto).get());
	}

	/*
	 * Recebe um produtoDto passado, converte para a entidade do banco, tenta
	 * inserir e caso o metodo save retorne o objeto salvo, retorna uma string de
	 * sucesso, caso nao lanca Excecao
	 * 
	 * @Param ProdutoDto
	 * 
	 * @return String de sucesso
	 * 
	 * @Throw ExcecaoNegocio
	 */
	@Transactional
	public String insert(ProdutoDto produtoDto) throws ExcecaoNegocio {
		Produto produto = new Produto(produtoDto.getId(), produtoDto.getNome(), produtoDto.getValor());

		if (produtoRepositorio.save(produto) != null) {
			return "Produto inserido com sucesso.";
		}

		throw new ExcecaoNegocio("Não foi possível cadastrar o cliente.");
	}

	/*
	 * Recebe um produtoDto passado, converte para a entidade do banco, tenta
	 * atualizar e caso de algum erro e lancada uma excecao
	 * 
	 * @Param ProdutoDto
	 * 
	 * @Throw ExcecaoNegocio
	 */
	@Transactional
	public void update(ProdutoDto produtoDto) throws ExcecaoNegocio {
		Produto produto = new Produto(produtoDto.getId(), produtoDto.getNome(), produtoDto.getValor());
		try {
			produtoRepositorio.updateProduto(produto.getNome(), produto.getValor(), produto.getId());
		} catch (Exception e) {
			throw new ExcecaoNegocio("Erro ao atualizar produto.");
		}

	}

	/*
	 * Recebe o id do produto a ser excluido, caso exista alguma venda é retornado o
	 * erro de integrity violation.
	 * 
	 * 
	 * @Param idProduto
	 * 
	 * @Throw DataIntegrityException
	 */
	public void delete(long idProduto) {
		try {
			produtoRepositorio.deleteById(idProduto);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel deletar o produto, pois tem uma venda vinculada.");
		}

	}
}
