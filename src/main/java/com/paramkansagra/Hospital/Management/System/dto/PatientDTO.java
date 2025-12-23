package com.paramkansagra.Hospital.Management.System.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private Long Id;

    @NotEmpty
    @Size(min = 3, max = 30, message = "Name must be size between 3 and 30")
    private String name;

    @NotEmpty
    @PastOrPresent
    private LocalDate birthdate;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Pattern(regexp = "^(male|female|other)$", message = "Gender must be 'male' , 'female' or 'other'")
    private String gender;
}
