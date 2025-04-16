package com.ekart.product.service;

import com.ekart.product.model.Products;
import com.ekart.product.repository.ProductRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    private MongoTemplate mongoTemplate;

    public ProductService(ProductRepository productRepository, MongoTemplate mongoTemplate) {
        this.productRepository = productRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public Products createProduct(Products product) {
        product.setCreate_date(LocalDateTime.now());
        return productRepository.save(product);
    }

    public List<Products> searchProducts(String searchTerm) {
        Query query = new Query();
        query.addCriteria(
                new Criteria().orOperator(
                        Criteria.where("name").regex(searchTerm, "i"),
                        Criteria.where("category").regex(searchTerm, "i")
                )
        );
        return mongoTemplate.find(query, Products.class);
    }
    public Products searchProduct(String searchTerm) {
        Query query = new Query();
        query.addCriteria(
                new Criteria().orOperator(
                        Criteria.where("name").regex(searchTerm, "i")
                )
        );
        return mongoTemplate.find(query, Products.class).get(0);
    }
}
