package com.droid.E_commerce.app.controller;

import com.droid.E_commerce.app.entity.Product;
import com.droid.E_commerce.app.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add/product")
    public String addProduct() {
        return "AddProduct";
    }

    @PostMapping("/add/product")
    public String addProduct(@RequestBody Product product) {
        productService.createProduct(product);

        return "/admin/home";
    }

    @GetMapping("/update/product/{id}")
    public String updateProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "UpdateProduct";
    }

    @GetMapping("/update/product")
    public String updateProduct(Product product) {
        productService.updateProduct(product);

        return "/admin/home";
    }

    @DeleteMapping("/delete/product/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

        return "/admin/home";
    }
}
