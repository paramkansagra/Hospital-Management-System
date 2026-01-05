package com.paramkansagra.Hospital.Management.System.services.servicesImpl;

import com.paramkansagra.Hospital.Management.System.dto.LoginRequestDTO;
import com.paramkansagra.Hospital.Management.System.dto.LoginResponseDTO;
import com.paramkansagra.Hospital.Management.System.dto.SignupRequestDTO;
import com.paramkansagra.Hospital.Management.System.dto.SignupResponseDTO;
import com.paramkansagra.Hospital.Management.System.entity.User;
import com.paramkansagra.Hospital.Management.System.repository.UserRepository;
import com.paramkansagra.Hospital.Management.System.security.AuthUtil;
import com.paramkansagra.Hospital.Management.System.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));

        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(Objects.requireNonNull(user));

        return new LoginResponseDTO(token, user.getId());
    }

    @Override
    public SignupResponseDTO signup(SignupRequestDTO signupRequestDTO) {
        if (userRepository.findByUsername(signupRequestDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException(String.format("User with username %s already exists", signupRequestDTO.getUsername()));
        }

        User user = userRepository.save(
                        User.builder()
                                .username(signupRequestDTO.getUsername())
                                .password(signupRequestDTO.getPassword())
                                .build()
        );

        return modelMapper.map(user , SignupResponseDTO.class);
    }
}
