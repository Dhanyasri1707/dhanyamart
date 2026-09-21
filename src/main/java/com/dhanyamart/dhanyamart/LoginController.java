package com.dhanyamart.dhanyamart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(
            @RequestParam String username,
            @RequestParam String password) {

        User user = userRepository.findByUsernameAndPassword(
                username,
                password
        );

        if (user != null) {

            if ("ADMIN".equals(user.getRole())) {
                return "redirect:/admin";
            }

            return "redirect:/products";
        }

        return "login";
    }
}