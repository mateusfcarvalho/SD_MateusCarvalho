package br.inatel.labs.labjpa.entity;

import jave.util.List;

import jakarta.persistence.*

@Entity
public class Fornecedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToMany //Dominante
	private List<Produto> listaProduto;

	@NotNull
	@Size(min = 2, max = 200)
	private String razaoSocial;

}