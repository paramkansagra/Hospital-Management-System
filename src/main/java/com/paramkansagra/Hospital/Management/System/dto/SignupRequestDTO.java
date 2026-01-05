package com.paramkansagra.Hospital.Management.System.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequestDTO {

    @NotEmpty
    private String username;

    @NotEmpty
    @Size(min = 8 , max = 30 , message = "Password size must be between 8 and 30 characters.")
    private String password;
}
