package br.inatel.labs.labjpa.entity;

import jave.util.List;

import jakarta.persistence.*

@Entity
public class NotaCompra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany
	private List<NotaCompraItem> listaNotaCompraItem;

	@ManyToOne
	private Fornecedor fornecedor;

	@NotNull
	@Past
	private LocalDate dataEmissao;
}