package br.com.blz.testjava.dto;

import lombok.Data;

@Data
public class ProdutoRequest {

    private Integer sku;
    private String name;
    private Inventory inventory;

    public ProdutoRequest(){}

    public ProdutoRequest(Integer sku, String name){
        this.sku = sku;
        this.name = name;
    }
}
