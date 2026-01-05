package com.paramkansagra.Hospital.Management.System.controller;

import com.paramkansagra.Hospital.Management.System.dto.LoginRequestDTO;
import com.paramkansagra.Hospital.Management.System.dto.LoginResponseDTO;
import com.paramkansagra.Hospital.Management.System.dto.SignupRequestDTO;
import com.paramkansagra.Hospital.Management.System.dto.SignupResponseDTO;
import com.paramkansagra.Hospital.Management.System.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok().body(authService.login(loginRequestDTO));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDTO> signup(@RequestBody SignupRequestDTO signupRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(signupRequestDTO));
    }
}
