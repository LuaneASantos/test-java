package br.com.blz.testjava.entity;

import br.com.blz.testjava.dto.Inventory;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "produtos")
public class Produto {

    @Id
    private String id;

    private Integer sku;
    private String name;
    private Inventory inventory;
    private boolean isMarketable;

    public Produto(){}

    public Produto(Integer sku, String name, Inventory inventory, boolean isMarketable){
        this.sku = sku;
        this.name = name;
        this.inventory = inventory;
        this.isMarketable = isMarketable;
    }
}
