package com.paramkansagra.Hospital.Management.System.entity;

import com.paramkansagra.Hospital.Management.System.dto.PatientDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false , unique = true , length = 50)
    private String policyNumber;

    @Column(nullable = false , length = 100)
    private String provider;

    @Column(nullable = false)
    private LocalDate validUntil;

    @CreationTimestamp
    @Column(nullable = false , updatable = false)
    private LocalDate createdAt;

    // to have bidirectional mapping we will do this
    // It is called the inverse side.
    @OneToOne(mappedBy = "insurance")
    private Patient patient;
}
