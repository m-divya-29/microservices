package com.md29.microservices.product.repository;

import com.md29.microservices.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends MongoRepository<Product, String> {
    Product findBySkuCode(String s);
}
