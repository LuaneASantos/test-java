package br.com.blz.testjava.service;

import br.com.blz.testjava.dto.ProdutoRequest;
import br.com.blz.testjava.entity.Produto;
import br.com.blz.testjava.erro.ErroData;
import br.com.blz.testjava.erro.ErroPersonalizado;
import br.com.blz.testjava.mapping.ProdutoMapper;
import br.com.blz.testjava.repository.ProdutoRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    ProdutoMapper produtoMapper;

    @Autowired
    ProdutoRepository produtoRepository;

    public Object incluirProduto(ProdutoRequest produtoRequest) {

        Produto produto = consultarProduto(produtoRequest.getSku());

        if (produto != null) {
            return erro("001", "Produto já existente!");
        }

        produto = produtoMapper.fromResource(produtoRequest);
        produtoRepository.save(produto);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    public void editarProduto(ProdutoRequest produtoRequest, Integer sku) {

        Produto produto = consultarProduto(sku);

        Produto produtoAtualizado = produtoMapper.fromResource(produtoRequest);
        produto.setInventory(produtoAtualizado.getInventory());
        produto.setName(produtoAtualizado.getName());

        produtoRepository.save(produto);
    }

    public Object consultarProdutoSku(Integer sku) {

        Produto produto = consultarProduto(sku);

        if (produto == null) {
            return erro("002", "Produto inexistente!");
        }

        produto.getInventory().setQuantity(produto.getInventory().getWarehouses().stream().mapToInt(i -> i.getQuantity().intValue()).sum());
        produto.setMarketable(produto.getInventory().getQuantity() > 0);

        return produto;
    }

    public void excluirProduto(Integer sku) {

        Produto produto = consultarProduto(sku);
        produtoRepository.delete(produto);
    }

    public Produto consultarProduto(Integer sku) {
        return produtoRepository.findBySku(sku);
    }

    public Object erro(String codigo, String descricao) {

        ErroData erro = ErroPersonalizado.erro(codigo, descricao);
        Gson gson = new Gson();
        return new ResponseEntity(gson.toJson(erro), HttpStatus.BAD_REQUEST);
    }
}
