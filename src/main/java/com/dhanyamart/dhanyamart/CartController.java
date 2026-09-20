package com.dhanyamart.dhanyamart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CartController {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartController(CartRepository cartRepository,
                          ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @PostMapping("/cart/add")
    public String addToCart(
            @RequestParam Long productId,
            @RequestParam String username) {

        Cart cart = new Cart(productId, username, 1);
        cartRepository.save(cart);

        return "redirect:/cart?username=" + username;
    }

    @GetMapping("/cart")
    public String viewCart(
            @RequestParam String username,
            Model model) {

        List<Cart> cartItems = cartRepository.findByUsername(username);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("products", productRepository.findAll());

        return "cart";
    }
}