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

import com.sm.solucoes.apirest.dto.VendaDataEntregaDto;
import com.sm.solucoes.apirest.dto.VendaDto;
import com.sm.solucoes.apirest.services.VendaService;
import com.sm.solucoes.apirest.services.excecao.ExcecaoNegocio;

@RestController
@RequestMapping(value = "/venda")
public class VendaController {

	@Autowired
	VendaService vendaServico;

	@GetMapping("/findAll")
	public ResponseEntity<List<VendaDto>> findAll() {
		return ResponseEntity.ok(vendaServico.getAll());
	}
	
	@GetMapping("/findAllDataEntrega")
	public ResponseEntity<List<VendaDataEntregaDto>> findAllDataEntrega() {
		return ResponseEntity.ok(vendaServico.getVendasComDataEntrega());
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<VendaDto> getById(@PathVariable("id") long idVenda) {
		return ResponseEntity.ok(vendaServico.getById(idVenda));
	}

	@PostMapping("/insert")
	public ResponseEntity<String> insert(@RequestBody VendaDto vendaDto) throws ExcecaoNegocio {
		return ResponseEntity.ok(vendaServico.insert(vendaDto));
	}

	@PostMapping("/update")
	public ResponseEntity<String> update(@RequestBody VendaDto vendaDto) throws ExcecaoNegocio {
		vendaServico.update(vendaDto);
		return ResponseEntity.ok("Venda atualizada com sucesso.");
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") long idVenda) throws ExcecaoNegocio {
		vendaServico.delete(idVenda);
		return ResponseEntity.ok("Venda deletada com sucesso.");
	}
}
