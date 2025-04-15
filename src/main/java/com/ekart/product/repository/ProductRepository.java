package com.ekart.product.repository;

import com.ekart.product.model.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Products,Integer> {
}
