package com.dhanyamart.dhanyamart;

import jakarta.servlet.http.HttpSession;
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
            @RequestParam String password,
            HttpSession session) {

        User user = userRepository.findByUsernameAndPassword(
                username,
                password
        );

        if (user != null) {

            // Get the actual username stored in the database
            String loggedInUsername = user.getUsername();

            // Store it in session
            session.setAttribute("username", loggedInUsername);

            if ("ADMIN".equals(user.getRole())) {
                return "redirect:/admin";
            }

            // Send the actual database username
            return "redirect:/products?username=" + loggedInUsername;
        }

        return "login";
    }
}