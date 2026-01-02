package com.paramkansagra.Hospital.Management.System.repository;

import com.paramkansagra.Hospital.Management.System.entity.Patient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient , Long> {
    List<Patient> findByName(String name);
    List<Patient> findByNameOrEmail(String name , String email);

    @Query("Select p from Patient p LEFT JOIN FETCH p.appointments a LEFT JOIN FETCH a.doctor")
    List<Patient> getAlPatientWithAppointment();
}