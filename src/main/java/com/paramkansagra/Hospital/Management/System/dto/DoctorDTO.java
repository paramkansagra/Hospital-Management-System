package com.paramkansagra.Hospital.Management.System.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {
    @NotEmpty
    private Long id;

    @NotEmpty
    @Size(max = 100)
    private String Name;

    @NotEmpty
    private String specialization;

    @NotEmpty
    @Size(max = 100)
    @Email
    private String email;

    @NotEmpty
    private LocalDateTime createdAt;
}
