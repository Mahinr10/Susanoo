package com.shinobi.susanoo.controller;

import com.shinobi.susanoo.entity.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

        LOGGER.info("post login started");

        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequest.getUsername(), loginRequest.getPassword()
        );


        Authentication authentication1 = authenticationManager.authenticate(authentication);

        LOGGER.info(authentication1.getCredentials().toString());
        LOGGER.info(authentication1.getName());
        LOGGER.info(authentication1.getDetails().toString());
        LOGGER.info(authentication1.getAuthorities().toString());

        return ResponseEntity.ok(authentication1.getCredentials().toString());
    }

    @GetMapping("/login")
    public String login() {
        LOGGER.info("GET login started");
        return "done";
    }

}
