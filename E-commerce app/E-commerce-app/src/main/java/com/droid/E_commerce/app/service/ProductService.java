package com.droid.E_commerce.app.service;

import com.droid.E_commerce.app.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void createProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Long id);

    Product findProductByName(String name);
}
