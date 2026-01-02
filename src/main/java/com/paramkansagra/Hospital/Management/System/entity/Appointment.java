package com.paramkansagra.Hospital.Management.System.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(length = 500)
    private String reason;

    // for each appointment we need a patient id, and thus it is not nullable,
    // and we do not need any cascading because if an appointment is created then we don't want the patient to be created
    @ManyToOne
    @JoinColumn(name = "patient_id" , nullable = false) // owning side
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id" , nullable = false) // owning side
    private Doctor doctor;
}
