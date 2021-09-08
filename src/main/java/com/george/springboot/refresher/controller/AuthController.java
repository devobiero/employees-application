package com.george.springboot.refresher.controller;

import com.george.springboot.refresher.security.JwtRequest;
import com.george.springboot.refresher.security.JwtResponse;
import com.george.springboot.refresher.security.JwtUtil;
import com.george.springboot.refresher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserService userService;

  @PostMapping("/login")
  public JwtResponse authenticate(@RequestBody JwtRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        )
    );

    UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
    String token = jwtUtil.generateToken(userDetails);
    return new JwtResponse(token);
  }
}
