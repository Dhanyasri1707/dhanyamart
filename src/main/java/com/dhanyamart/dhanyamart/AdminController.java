package com.dhanyamart.dhanyamart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;

    public AdminController(
            UserRepository userRepository,
            ProductRepository productRepository,
            OrderRepository orderRepository,
            ReviewRepository reviewRepository) {

        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {

        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("orders", orderRepository.findAll());

        return "admin";
    }

    @GetMapping("/admin/orders")
    public String adminOrders(Model model) {

        model.addAttribute("orders", orderRepository.findAll());

        return "admin-orders";
    }

    @GetMapping("/admin/products")
    public String adminProducts(Model model) {

        model.addAttribute("products", productRepository.findAll());

        return "admin-products";
    }

    @GetMapping("/admin/reviews")
    public String adminReviews(Model model) {

        model.addAttribute("reviews", reviewRepository.findAll());

        return "admin-reviews";
    }
}