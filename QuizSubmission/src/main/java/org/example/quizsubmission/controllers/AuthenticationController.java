package org.example.quizsubmission.controllers;

import org.example.quizsubmission.model.User;
import org.example.quizsubmission.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping( "/login")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }
    @GetMapping
    public String showLoginPage() {
        return "login";
    }

    @PostMapping
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpServletResponse response) {
        User user = new User(username, password);
        ResponseEntity<String> authResponse = authenticationService.authenticate(user);
        HttpStatus status = (HttpStatus) authResponse.getStatusCode();
        if (status == HttpStatus.OK) {
            return "redirect:/quiz/1";
        } else if (status == HttpStatus.UNAUTHORIZED) {
            model.addAttribute("error_msg", "Wrong credentials. Please try again.");
        } else {
            model.addAttribute("error_msg", "Authentication failed.");
        }
        return "login";
    }
}