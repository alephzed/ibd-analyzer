package com.herringbone.ibdanalyzer.repository;

import com.herringbone.ibdanalyzer.entity.IBDStock;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IBDStockRepository extends MongoRepository<IBDStock, String> {

    public Optional<IBDStock> findBySymbol(String symbol);
//    public List<IBDStock> findBy(String lastName);

}
