package com.paramkansagra.Hospital.Management.System.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDTO {

    @NotEmpty
    private String username;

    @Size(max = 30 , message = "Password size must be max 30 characters.")
    private String password;
}
