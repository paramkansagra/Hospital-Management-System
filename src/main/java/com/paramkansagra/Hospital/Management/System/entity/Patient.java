package com.paramkansagra.Hospital.Management.System.entity;

import com.paramkansagra.Hospital.Management.System.entity.PatientEnums.BloodGroup;
import com.paramkansagra.Hospital.Management.System.entity.PatientEnums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@ToString // This is for converting it to string
@Getter
@Setter
@Table(
        name = "patient",
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

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(updatable = false)
    @CreationTimestamp
    private String createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    // This is showing one-to-one relationship between insurance and patient
    // This is the owning side of the relationship
    @OneToOne(cascade = CascadeType.ALL , orphanRemoval = true) // if insurance doesn't have any parents it will be deleted automatically
    // whenever we are adding a new insurance it should be saved automatically and it should persist
    @JoinColumn(name = "patient_insurance_id")
    private Insurance insurance;

    // this is the inverse side of the relationship
    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE}, orphanRemoval = true) // FetchType.EAGER means that when the patient is fetched all the appointments attached to that patient are fetched too
    // orphan removal means if the parent is removed then all its children would be removed
    private Set<Appointment> appointments;
}
