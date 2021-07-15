package br.com.blz.testjava.mapping;

import br.com.blz.testjava.dto.ProdutoRequest;
import br.com.blz.testjava.entity.Produto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    Produto fromResource(ProdutoRequest produtoRequest);

}
