package com.ekart.product.controller;

import com.ekart.product.model.Products;
import com.ekart.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Products> createProduct(@RequestBody Products product) {
        Products savedProduct = productService.createProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Products>> searchProducts(@RequestParam String term) {
        List<Products> products = productService.searchProducts(term);
        return ResponseEntity.ok(products);
    }
}
