package com.dhanyamart.dhanyamart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    private final UserRepository userRepository;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        // Check empty fields
        if (username == null || username.trim().isEmpty()) {
            model.addAttribute("error", "Username cannot be empty");
            return "register";
        }

        if (password == null || password.trim().isEmpty()) {
            model.addAttribute("error", "Password cannot be empty");
            return "register";
        }

        // Check password length
        if (password.length() < 6) {
            model.addAttribute("error", "Password must contain at least 6 characters");
            return "register";
        }

        // Check whether username already exists
        if (userRepository.findByUsername(username) != null) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }

        User user = new User(username, password, "USER");
        userRepository.save(user);

        return "redirect:/";
    }
}