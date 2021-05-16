package com.sm.solucoes.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sm.solucoes.apirest.dto.ClienteDto;
import com.sm.solucoes.apirest.services.ClienteService;
import com.sm.solucoes.apirest.services.excecao.ExcecaoNegocio;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteServico;

	@GetMapping("/findAll")
	public ResponseEntity<List<ClienteDto>> findAll() {
		return ResponseEntity.ok(clienteServico.getAll());
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<ClienteDto> getById(@PathVariable("id") long idCliente) {
		return ResponseEntity.ok(clienteServico.getById(idCliente));
	}

	@PostMapping("/insert")
	public ResponseEntity<String> insert(@RequestBody ClienteDto clienteDto) throws ExcecaoNegocio {
		return ResponseEntity.ok(clienteServico.insert(clienteDto));
	}

	@PostMapping("/update")
	public ResponseEntity<String> update(@RequestBody ClienteDto clienteDto) throws ExcecaoNegocio {
		clienteServico.update(clienteDto);
		return ResponseEntity.ok("Cliente atualizado com sucesso.");
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") long idCliente) throws ExcecaoNegocio {
		clienteServico.delete(idCliente);
		return ResponseEntity.ok("Cliente deletado com sucesso.");
	}
}
