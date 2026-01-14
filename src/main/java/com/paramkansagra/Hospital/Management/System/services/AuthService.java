package com.paramkansagra.Hospital.Management.System.services;

import com.paramkansagra.Hospital.Management.System.dto.LoginRequestDTO;
import com.paramkansagra.Hospital.Management.System.dto.LoginResponseDTO;
import com.paramkansagra.Hospital.Management.System.dto.SignupRequestDTO;
import com.paramkansagra.Hospital.Management.System.dto.SignupResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
    SignupResponseDTO signup(SignupRequestDTO signupRequestDTO);
    ResponseEntity<LoginResponseDTO> handleOAuth2LoginRequest(OAuth2User oAuth2User , String registrationId);
}
