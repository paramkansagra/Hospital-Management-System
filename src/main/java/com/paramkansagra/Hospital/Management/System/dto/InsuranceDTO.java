package com.paramkansagra.Hospital.Management.System.dto;

import com.paramkansagra.Hospital.Management.System.entity.PatientEnums.BloodGroup;
import com.paramkansagra.Hospital.Management.System.entity.PatientEnums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceDTO {
    @NotEmpty
    private Long Id;

    @NotEmpty
    @Size(min = 1, max = 50, message = "Policy Number must be size between 1 and 50")
    private String policyNumber;

    @NotEmpty
    @Size(min = 1 , max = 100 , message = "Provider Name must be size between 1 and 100")
    private String provider;

    @NotEmpty
    private LocalDate validUntil;

    @NotEmpty
    private LocalDate createdAt;

    @NotEmpty
    private Long patient_insurance_id;
}
