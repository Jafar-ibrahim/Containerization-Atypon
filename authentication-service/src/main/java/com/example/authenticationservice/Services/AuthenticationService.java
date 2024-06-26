package com.example.authenticationservice.Services;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.authenticationservice.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.authenticationservice.Repository.UserRepository;
import com.auth0.jwt.JWT;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Objects;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }
        return Objects.equals(user.getPassword(), password);
    }

}
