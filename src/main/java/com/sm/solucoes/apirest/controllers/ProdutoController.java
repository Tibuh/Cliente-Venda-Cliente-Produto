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

import com.sm.solucoes.apirest.dto.ProdutoDto;
import com.sm.solucoes.apirest.services.ProdutoService;
import com.sm.solucoes.apirest.services.excecao.ExcecaoNegocio;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

	@Autowired
	ProdutoService produtoServico;

	@GetMapping("/findAll")
	public ResponseEntity<List<ProdutoDto>> findAll() {
		return ResponseEntity.ok(produtoServico.getAll());
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<ProdutoDto> getById(@PathVariable("id") long idProduto) {
		return ResponseEntity.ok(produtoServico.getById(idProduto));
	}

	@PostMapping("/insert")
	public ResponseEntity<String> insert(@RequestBody ProdutoDto produtoDto) throws ExcecaoNegocio {
		return ResponseEntity.ok(produtoServico.insert(produtoDto));
	}

	@PostMapping("/update")
	public ResponseEntity<String> update(@RequestBody ProdutoDto produtoDto) throws ExcecaoNegocio {
		produtoServico.update(produtoDto);
		return ResponseEntity.ok("Produto atualizado com sucesso.");
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long idProduto) throws ExcecaoNegocio {
		produtoServico.delete(idProduto);
		return ResponseEntity.ok("Produto deletado com sucesso.");
	}
}
