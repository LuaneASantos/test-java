package br.com.blz.testjava.controller;

import br.com.blz.testjava.dto.ProdutoRequest;
import br.com.blz.testjava.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(value = "/api/produto", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Object incluirProduto(@RequestBody ProdutoRequest produtoRequest){
        return produtoService.incluirProduto(produtoRequest);
    }

    @RequestMapping(value = "/api/produto/{sku}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Object editarProduto(@RequestBody ProdutoRequest produtoRequest, @PathVariable("sku") Integer sku){
        produtoService.editarProduto(produtoRequest, sku);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/produto/{sku}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Object consultarProduto(@PathVariable("sku") Integer sku) {
        return produtoService.consultarProdutoSku(sku);
    }

    @RequestMapping(value = "/api/produto/{sku}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public Object excluirProduto(@PathVariable("sku") Integer sku) {
        produtoService.excluirProduto(sku);
        return new ResponseEntity(HttpStatus.OK);
    }

}
