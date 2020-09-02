package com.pz.NNServer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pz.NNServer.dto.AuthenticationResponse;
import com.pz.NNServer.dto.LoginRequest;
import com.pz.NNServer.dto.RegisterRequest;
import com.pz.NNServer.service.AuthService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity<String>("User registration successfull", HttpStatus.OK);

    }
    
    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> veryfyAccount(@PathVariable String token){
    	authService.verifyAccount(token);
    	return new ResponseEntity<String>("Account activated succesfully",HttpStatus.OK);
    }
    
    @GetMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest){
    	return authService.login(loginRequest);
    }
   
}
