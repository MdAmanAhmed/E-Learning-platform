package com.cognizant.project.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.project.elearning.dto.LoginRequestDTO;
import com.cognizant.project.elearning.dto.LoginResponseDTO;
import com.cognizant.project.elearning.dto.RegisterRequestDTO;
import com.cognizant.project.elearning.dto.RegisterResponseDTO;
import com.cognizant.project.elearning.service.AuthenticationService;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

//    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
//        logger.info("Entering register method with registerRequestDTO: {}", registerRequestDTO);
        ResponseEntity<RegisterResponseDTO> response = new ResponseEntity<>(authenticationService.register(registerRequestDTO), HttpStatus.OK);
//        logger.info("Exiting register method with response: {}", response);
        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
//        logger.info("Entering login method with loginRequestDTO: {}", loginRequestDTO);
        ResponseEntity<LoginResponseDTO> response = new ResponseEntity<>(authenticationService.login(loginRequestDTO), HttpStatus.OK);
//        logger.info("Exiting login method with response: {}", response);
        return response;
    }
}
