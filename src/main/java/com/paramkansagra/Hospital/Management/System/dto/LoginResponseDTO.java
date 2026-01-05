package com.paramkansagra.Hospital.Management.System.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {

    @NotEmpty
    public String jwt;

    @NotEmpty
    public Long userId;
}
