package com.droid.E_commerce.app.service;

import com.droid.E_commerce.app.entity.Product;
import com.droid.E_commerce.app.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException(String.format("Product with id %s not found", id))
                );
    }

    @Transactional
    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Transactional
    @Override
    public void updateProduct(Product product) {
        productRepository.findById(product.getId())
                .orElseThrow(
                        () -> new RuntimeException("Product with id %s not found".formatted(product.getId()))
                );

        productRepository.save(product);
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Product with id %s not found".formatted(id))
                );

        productRepository.deleteById(id);
    }

    @Override
    public Product findProductByName(String name) {
        return productRepository.findByName(name);
    }
}
