package com.group9.eventgaze.service;

import com.group9.eventgaze.entity.Users;
import com.group9.eventgaze.entity.dto.LoginRequest;
import com.group9.eventgaze.entity.dto.LoginResponse;
import com.group9.eventgaze.repository.UsersRepo;
import com.group9.eventgaze.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LoginService {

    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    public LoginResponse logIn(LoginRequest loginRequest) {
        // Retrieve the user by email
        Users users = usersRepo.findByUserEmail(loginRequest.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        // Validate password
        if (!passwordEncoder.matches(loginRequest.getPassword(), users.getUserPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        // Generate JWT token (set expiration time to 1 hour for example)
        String jwtToken = jwtTokenUtil.generateToken(users.getUserId(), users.getUserRole());

        return new LoginResponse(jwtToken, users.getUserRole(), users.getUserId());
    }

}
