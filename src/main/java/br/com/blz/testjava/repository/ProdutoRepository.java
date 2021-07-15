package br.com.blz.testjava.repository;

import br.com.blz.testjava.entity.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {

    @Query("{ 'sku' : ?0}")
    Produto findBySku(Integer sku);
}
