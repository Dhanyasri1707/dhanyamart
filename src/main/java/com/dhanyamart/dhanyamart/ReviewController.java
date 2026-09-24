package com.dhanyamart.dhanyamart;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    public ReviewController(
            ReviewRepository reviewRepository,
            ProductRepository productRepository) {

        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/review")
    public String reviewPage(
            @RequestParam Long productId,
            @RequestParam(required = false) String username,
            HttpSession session,
            Model model) {

        // Get username from URL first
        // If it is missing, get it from the login session
        if (username == null || username.trim().isEmpty()) {
            username = (String) session.getAttribute("username");
        }

        Product product =
                productRepository.findById(productId).orElse(null);

        List<Review> reviews =
                reviewRepository.findByProductId(productId);

        model.addAttribute("product", product);
        model.addAttribute("productId", productId);
        model.addAttribute("username", username);
        model.addAttribute("reviews", reviews);

        return "reviews";
    }

    @PostMapping("/review")
    public String saveReview(
            @RequestParam Long productId,
            @RequestParam(required = false) String username,
            @RequestParam int rating,
            @RequestParam String comment,
            HttpSession session) {

        // If username is missing, get it from logged-in session
        if (username == null || username.trim().isEmpty()) {
            username = (String) session.getAttribute("username");
        }

        // Save the review
        Review review = new Review(
                username,
                productId,
                rating,
                comment
        );

        reviewRepository.save(review);

        return "redirect:/review?productId="
                + productId
                + "&username="
                + username;
    }
}