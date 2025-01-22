package com.pos.repository;

import com.pos.model.pointOfSales;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface pointOfSalesRepository extends MongoRepository<pointOfSales, Long> {
}


