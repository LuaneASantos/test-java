package br.com.blz.testjava.dto;

import lombok.Data;

import java.util.List;

@Data
public class Inventory {

    private Integer quantity;
    private List<Warehouses> warehouses;
}
