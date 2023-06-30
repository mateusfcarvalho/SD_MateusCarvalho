package br.inatel.labs.labrest.server.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProdutoControllerTest {

	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	void deveListarProdutos() {
		webTestClient
		.get()
		.uri("/produto")
		.exchange()
		.expectStatus()
			.isOk() //status is OK?
		.expectBody()
			.returnResult() //retornou resultado?
		;
	}


}
