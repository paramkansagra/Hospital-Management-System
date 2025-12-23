package com.paramkansagra.Hospital.Management.System.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@ToString // This is for converting it to string
@Table(
        name = "patient_tbl",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_patient_email", columnNames = {"email"}),
                @UniqueConstraint(name = "unique_patient_name_birthdate", columnNames = {"name", "birthdate"})
        },
        indexes = {
                @Index(name = "idx_patient_birthdate", columnList = "birthdate"),
        }
)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @ToString.Exclude // in case we don't want to see this field in ToString function
    @PastOrPresent // we can only have past or preset as the birthdate
    private LocalDate birthdate;

    @Column(unique = true , nullable = false)
    private String email;

    private String gender;

    @Column(updatable = false)
    @CreationTimestamp
    private String createdAt;
}
