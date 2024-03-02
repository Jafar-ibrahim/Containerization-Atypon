package org.example.quizanalytics.Controllers;

import org.example.quizanalytics.Model.User;
import org.example.quizanalytics.Service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping( "/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

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
            return "redirect:/quiz/analytics/1";
        } else if (status == HttpStatus.UNAUTHORIZED) {
            model.addAttribute("error_msg", "Wrong credentials. Please try again.");
        } else {
            model.addAttribute("error_msg", "Authentication failed.");
        }
        return "login";
    }
}