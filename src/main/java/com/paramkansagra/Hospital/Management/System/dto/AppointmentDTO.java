package com.paramkansagra.Hospital.Management.System.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    @NotEmpty
    private Long Id;

    @NotEmpty
    private LocalDateTime appointmentTime;

    @NotEmpty
    @Size(max = 500)
    private String reason;

    @NotEmpty
    private PatientDTO patientId;

    @NotEmpty
    private DoctorDTO doctorId;
}
