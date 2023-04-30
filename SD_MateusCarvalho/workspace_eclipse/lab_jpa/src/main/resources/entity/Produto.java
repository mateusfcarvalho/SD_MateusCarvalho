package br.inatel.labs.labjpa.entity;

import jave.util.List;

import jakarta.persistence.*

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToMany (mappedBy= "listaProduto")
	private List<Fornecedor> listaFornecedor;

	@NotNull
	@Size(min = 2, max = 100)
	private String descricao;
	
}

