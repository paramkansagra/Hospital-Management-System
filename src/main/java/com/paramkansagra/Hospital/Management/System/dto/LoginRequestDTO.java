package com.paramkansagra.Hospital.Management.System.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    @NotEmpty
    private String username;

    @NotEmpty
    @Size(min = 8 , max = 30 , message = "Password size must be between 8 and 30 characters.")
    private String password;
}
