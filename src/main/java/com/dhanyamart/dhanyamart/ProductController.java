package com.dhanyamart.dhanyamart;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public String products(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String username,
            HttpSession session,
            Model model) {

        // Get username from URL
        // If missing, get it from the login session
        if (username == null || username.trim().isEmpty()) {
            username = (String) session.getAttribute("username");
        }

        List<Product> products;

        if (search != null && !search.trim().isEmpty()) {
            products = productRepository
                    .findByNameContainingIgnoreCase(search);
        } else {
            products = productRepository.findAll();
        }

        model.addAttribute("products", products);
        model.addAttribute("search", search);
        model.addAttribute("username", username);

        return "products";
    }
}