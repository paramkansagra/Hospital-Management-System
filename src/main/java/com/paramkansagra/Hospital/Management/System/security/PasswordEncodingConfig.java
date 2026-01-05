package com.paramkansagra.Hospital.Management.System.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class PasswordEncodingConfig {

    @Bean
    public static PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public UserDetailsService userDetailsService(){
        UserDetails user1 = User
                .withUsername("admin")
                .password(getPasswordEncoder().encode("pass"))
                .roles("ADMIN")
                .build();

        UserDetails user2 = User
                .withUsername("patient")
                .password(getPasswordEncoder().encode("pass"))
                .roles("PATIENT")
                .build();

        UserDetails user3 = User
                .withUsername("doctor")
                .password(getPasswordEncoder().encode("pass"))
                .roles("DOCTOR")
                .build();

        return new InMemoryUserDetailsManager(user1 , user2 , user3);
    }
}
