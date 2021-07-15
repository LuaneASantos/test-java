package br.com.blz.testjava;

import br.com.blz.testjava.dto.Inventory;
import br.com.blz.testjava.dto.ProdutoRequest;
import br.com.blz.testjava.entity.Produto;
import br.com.blz.testjava.repository.ProdutoRepository;
import br.com.blz.testjava.service.ProdutoService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.http.HttpStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestJavaApplicationTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProdutoService produtoService;

    @MockBean
    private ProdutoRepository produtoRepository;

    Gson gson = new Gson();

    @Test
    public void consultarProdutoTest() throws Exception {
        given(produtoService.consultarProdutoSku(1)).willReturn(new Produto(1, "produto teste", new Inventory(), false));

        MockHttpServletResponse response = mvc.perform(get("/api/produto/1")
            .accept(MediaType.APPLICATION_JSON))
            .andReturn()
            .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(gson.fromJson(response.getContentAsString(), Produto.class).getSku()).isEqualTo(1);
    }

    @Test
    public void incluirProdutoTest() throws Exception {
        given(produtoService.incluirProduto(new ProdutoRequest(1, "produto teste"))).willReturn(new ResponseEntity(HttpStatus.CREATED));

        MockHttpServletResponse response = mvc.perform(post("/api/produto")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(
                gson.toJson(new ProdutoRequest(1, "produto teste"))
            ))
            .andReturn()
            .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void editarProdutoTest() throws Exception {

        MockHttpServletResponse response = mvc.perform(post("/api/produto/1")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(
                gson.toJson(new ProdutoRequest(1, "produto teste alterado"))
            ))
            .andReturn()
            .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void excluirProdutoTest() throws Exception {

        MockHttpServletResponse response = mvc.perform(delete("/api/produto/1")
            .accept(MediaType.APPLICATION_JSON))
            .andReturn()
            .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}
