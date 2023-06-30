package br.inatel.labs.labrest_client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.inatel.labs.labrest_client.model.dto.ProdutoDTO;

@SpringBootTest
class ProdutoControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void produtosDevemSerListados() {
        webTestClient.get()
                .uri("/produto")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .returnResult();
    }

    @Test
    void dadoProdutoIdValido_quandoGetProdutoPeloId_entaoRespondeComProdutoValido() {
        Long produtoIdValido = 1L;
        ProdutoDTO produtoRespondido = webTestClient.get()
                .uri("/produto/" + produtoIdValido)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProdutoDTO.class)
                .returnResult()
                .getResponseBody();
        assertNotNull(produtoRespondido);
        assertEquals(produtoRespondido.getId(), produtoIdValido);
    }

    @Test
    void dadoProdutoValido_quandoPostProduto_entaoResponseComStatusCreatedProdutoValido() {
        ProdutoDTO produto = new ProdutoDTO();
        produto.setDescricao("Broca");
        produto.setPreco(new BigDecimal("10.0"));

        ProdutoDTO produtoResponse = webTestClient.post()
                .uri("/produto")
                .bodyValue(produto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(ProdutoDTO.class)
                .returnResult()
                .getResponseBody();

        assertThat(produtoResponse).isNotNull();
        assertThat(produtoResponse.getId()).isNotNull();
    }

    @Test
    void dadoProdutoValido_quandoPutProduto_entaoResponseComStatusAccepted() {
        ProdutoDTO produto = new ProdutoDTO();
        produto.setId(1L);
        produto.setDescricao("Produto atualizado");
        produto.setPreco(new BigDecimal("20.0"));

        webTestClient.put()
                .uri("/produto")
                .bodyValue(produto)
                .exchange()
                .expectStatus().isAccepted()
                .expectBody()
                .isEmpty();
    }

    @Test
    void dadoProdutoIdValido_quandoDeleteProdutoPeloId_entaoResponseComStatusNoContent() {
        Long produtoValido = 2L;

        webTestClient.delete()
                .uri("/produto/" + produtoValido)
                .exchange()
                .expectStatus().isNoContent()
                .expectBody()
                .isEmpty();
    }

    @Test
    void dadoProdutoIdInvalido_quandoDeleteProdutoPeloId_entaoResponseComStatusNotFound() {
        Long produtoInvalido = 99L;

        webTestClient.delete()
                .uri("/produto/" + produtoInvalido)
                .exchange()
                .expectStatus().isNotFound();
    }
}
