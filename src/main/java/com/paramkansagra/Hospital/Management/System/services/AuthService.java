package com.paramkansagra.Hospital.Management.System.services;

import com.paramkansagra.Hospital.Management.System.dto.LoginRequestDTO;
import com.paramkansagra.Hospital.Management.System.dto.LoginResponseDTO;
import com.paramkansagra.Hospital.Management.System.dto.SignupRequestDTO;
import com.paramkansagra.Hospital.Management.System.dto.SignupResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
    SignupResponseDTO signup(SignupRequestDTO signupRequestDTO);
}
