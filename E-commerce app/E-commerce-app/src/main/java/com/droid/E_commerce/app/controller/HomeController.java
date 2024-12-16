package com.droid.E_commerce.app.controller;

import com.droid.E_commerce.app.entity.Admin;
import com.droid.E_commerce.app.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String homepage() {
        return "Home";
    }

    @GetMapping("/products")
    public String productPage(Model model) {
        model.addAttribute("productList", productService.getAllProducts());

        return "ProductPage";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("admin", new Admin());

        return "Login";
    }

    @GetMapping("/contactUs")
    public String contactPage() {
        return "ContactPage";
    }

    @GetMapping("/aboutUs")
    public String aboutUs() {
        return "AboutUs";
    }
}
