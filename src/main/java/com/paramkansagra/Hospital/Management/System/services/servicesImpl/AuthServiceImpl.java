package com.paramkansagra.Hospital.Management.System.services.servicesImpl;

import com.paramkansagra.Hospital.Management.System.dto.LoginRequestDTO;
import com.paramkansagra.Hospital.Management.System.dto.LoginResponseDTO;
import com.paramkansagra.Hospital.Management.System.dto.SignupRequestDTO;
import com.paramkansagra.Hospital.Management.System.dto.SignupResponseDTO;
import com.paramkansagra.Hospital.Management.System.entity.AuthProviderEnums.AuthProviderTypeEnums;
import com.paramkansagra.Hospital.Management.System.entity.User;
import com.paramkansagra.Hospital.Management.System.repository.UserRepository;
import com.paramkansagra.Hospital.Management.System.security.AuthUtil;
import com.paramkansagra.Hospital.Management.System.services.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword()
                )
        );

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
                        .password(
                                passwordEncoder.encode(signupRequestDTO.getPassword())
                        )
                        .build()
        );

        return modelMapper.map(user, SignupResponseDTO.class);
    }

    public User signupInternal(SignupRequestDTO signupRequestDTO , AuthProviderTypeEnums authProviderTypeEnums , String providerId){

        User user = userRepository.findByUsername(signupRequestDTO.getUsername()).orElse(null);

        if(user != null){
            throw new IllegalArgumentException("User already exists");
        }

        user = User.builder()
                .username(signupRequestDTO.getUsername())
                .providerId(providerId)
                .providerType(authProviderTypeEnums)
                .build();

        if(authProviderTypeEnums == AuthProviderTypeEnums.EMAIL){
            user.setPassword(passwordEncoder.encode(signupRequestDTO.getPassword()));
        }

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public ResponseEntity<LoginResponseDTO> handleOAuth2LoginRequest(OAuth2User oAuth2User, String registrationId) {
        // fetch the provider type and provider id
        // save the providerType and providerId info with the user
        // if the user has an account: directly login
        // else: signup and then login

        AuthProviderTypeEnums authProviderTypeEnums = authUtil.getProviderTypeFromRegistrationId(registrationId);
        String providerId = authUtil.determineProviderIdFromOAuth2User(oAuth2User , authProviderTypeEnums);

        User user = userRepository.findByProviderIdAndProviderType(providerId , authProviderTypeEnums).orElse(null);

        String email =  oAuth2User.getAttribute("email");
        User emailUser = userRepository.findByUsername(email).orElse(null);

        if(user == null && emailUser == null){
            // make a new user
            String username = authUtil.determineUsernameFromOAuth2User(oAuth2User , authProviderTypeEnums , providerId);

            SignupRequestDTO signupRequestDTO = new SignupRequestDTO(username , null);

            user = signupInternal(signupRequestDTO , authProviderTypeEnums , providerId);
        } else if(user != null){
            if(email != null && !email.isBlank() && !email.equals(user.getUsername())){
                user.setUsername(email);
                user = userRepository.save(user);
            }
        } else {
            throw new BadCredentialsException("This email is already registered with provider " + emailUser.getProviderType());
        }

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(authUtil.generateAccessToken(user) , user.getId());

        return ResponseEntity.ok(loginResponseDTO);
    }
}
