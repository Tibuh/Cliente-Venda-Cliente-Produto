package com.sm.solucoes.apirest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sm.solucoes.apirest.dto.ClienteDto;
import com.sm.solucoes.apirest.entities.Cliente;
import com.sm.solucoes.apirest.repositories.ClienteRepository;
import com.sm.solucoes.apirest.repositories.VendaRepository;
import com.sm.solucoes.apirest.services.excecao.DataIntegrityException;
import com.sm.solucoes.apirest.services.excecao.ExcecaoNegocio;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepositorio;

	@Autowired
	VendaRepository vendaRepositorio;

	/*
	 * Retorna todos os clientes, é feito uma conversão do objeto que vem do banco
	 * para DTO. Possui um Read Only pois este serviço no acesso ao banco, só irá
	 * fazer uma leitura dos dados.
	 * 
	 * @return List<ClienteDto>
	 */
	@Transactional(readOnly = true)
	public List<ClienteDto> getAll() {
		return clienteRepositorio.findAll().stream().map(cliente -> new ClienteDto(cliente))
				.collect(Collectors.toList());
	}

	/*
	 * Retorna o cliente com o Id passado
	 * 
	 * @return ProdutoDto
	 */
	@Transactional(readOnly = true)
	public ClienteDto getById(long idCliente) {
		return new ClienteDto(clienteRepositorio.findById(idCliente).get());
	}

	/*
	 * Recebe um clienteDto passado, converte para a entidade do banco, tenta
	 * inserir e caso o metodo save retorne o objeto salvo, retorna uma string de
	 * sucesso, caso nao lanca Excecao
	 * 
	 * @Param clienteDto
	 * 
	 * @return String de sucesso
	 * 
	 * @Throw ExcecaoNegocio
	 */
	@Transactional
	public String insert(ClienteDto clienteDto) throws ExcecaoNegocio {
		Cliente cliente = new Cliente(clienteDto.getId(), clienteDto.getNome(), clienteDto.getCpfCnpj());

		if (clienteRepositorio.save(cliente) != null) {
			return "Cliente inserido com sucesso.";
		}

		throw new ExcecaoNegocio("Não foi possível cadastrar o cliente.");
	}

	/*
	 * Recebe um clienteDto passado, e tenta da o update, caso de algum erro, é
	 * lançada uma excecao.
	 * 
	 * @Param clienteDto
	 * 
	 * @Throw ExcecaoNegocio
	 */
	@Transactional
	public void update(ClienteDto clienteDto) throws ExcecaoNegocio {
		try {
			clienteRepositorio.updateCliente(clienteDto.getNome(), clienteDto.getCpfCnpj(), clienteDto.getId());
		} catch (Exception e) {
			throw new ExcecaoNegocio("Erro ao atualizar produto.");
		}
	}

	/*
	 * Recebe o id do cliente a ser excluido, caso exista alguma venda é retornado o
	 * erro de integrity violation.
	 * 
	 * 
	 * @Param idCliente
	 * 
	 * @Throw DataIntegrityException
	 */
	public void delete(long idCliente) {
		try {
			clienteRepositorio.deleteById(idCliente);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel deletar o cliente, pois existe uma venda vinculada.");
		}
	}
}
