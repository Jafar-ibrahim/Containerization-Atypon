package com.example.authenticationservice.Controllers;

import com.example.authenticationservice.Model.User;
import com.example.authenticationservice.Services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<String> authenticate(@RequestBody User user) {
        if (!authenticationService.authenticateUser(user.getUsername(), user.getPassword())) {
            return new ResponseEntity<>("Wrong credentials try again", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}