package com.dhanyamart.dhanyamart;

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

    public ReviewController(ReviewRepository reviewRepository,
                            ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    @PostMapping("/review/add")
    public String addReview(
            @RequestParam String username,
            @RequestParam Long productId,
            @RequestParam int rating,
            @RequestParam String comment) {

        Review review = new Review(
                username,
                productId,
                rating,
                comment
        );

        reviewRepository.save(review);

        return "redirect:/reviews?productId=" + productId;
    }

    @GetMapping("/reviews")
    public String viewReviews(
            @RequestParam Long productId,
            Model model) {

        List<Review> reviews =
                reviewRepository.findByProductId(productId);

        Product product =
                productRepository.findById(productId).orElse(null);

        model.addAttribute("reviews", reviews);
        model.addAttribute("product", product);

        return "reviews";
    }
}