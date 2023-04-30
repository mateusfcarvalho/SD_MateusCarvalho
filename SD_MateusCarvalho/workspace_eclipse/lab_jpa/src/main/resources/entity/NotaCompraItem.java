package br.inatel.labs.labjpa.entity;

import jave.util.List;

import jakarta.persistence.*

@Entity
public class NotaCompraItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private NotaCompra notaCompra;

	@ManyToOne
	private Produto produto;

	@NotNull
	@Positive
	private BigDecimal valorCompraProduto;

	@NotNull
	@Positive
	private Integer quantidade;
}