package com.sm.solucoes.apirest.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Table(name = "tb_venda")
public class Venda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDate data;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	// Foi feito de forma unidirecional o relacionamento N:N, por ser um projeto
	// simples,
	// e poor não ser definido se um produto poderia saber nas vendas que ele
	// poderia estar em mais de uma venda
	@ManyToMany
	@JoinTable(name = "tb_venda_produto", joinColumns = @JoinColumn(name = "id_venda"), inverseJoinColumns = @JoinColumn(name = "id_produto"))
	private List<Produto> produtos = new ArrayList<>();

	public Venda(long id, LocalDate data, Cliente cliente) {
		this.id = id;
		this.data = data;
		this.cliente = cliente;
	}

}
