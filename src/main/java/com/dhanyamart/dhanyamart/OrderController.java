package com.dhanyamart.dhanyamart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderController(OrderRepository orderRepository,
                           ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @PostMapping("/order/place")
    public String placeOrder(
            @RequestParam String username,
            @RequestParam Long productId,
            @RequestParam int quantity,
            @RequestParam double total) {

        Order order = new Order(
                username,
                productId,
                quantity,
                total,
                "PLACED"
        );

        orderRepository.save(order);

        return "redirect:/orders?username=" + username;
    }

    @GetMapping("/orders")
    public String viewOrders(
            @RequestParam String username,
            Model model) {

        List<Order> orders = orderRepository.findByUsername(username);

        model.addAttribute("orders", orders);
        model.addAttribute("products", productRepository.findAll());

        return "orders";
    }

    @PostMapping("/admin/order/status")
    public String updateOrderStatus(
            @RequestParam Long orderId,
            @RequestParam String status) {

        Order order = orderRepository.findById(orderId).orElse(null);

        if (order != null) {
            order.setStatus(status);
            orderRepository.save(order);
        }

        return "redirect:/admin";
    }
}