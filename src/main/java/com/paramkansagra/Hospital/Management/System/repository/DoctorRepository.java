package com.paramkansagra.Hospital.Management.System.repository;

import com.paramkansagra.Hospital.Management.System.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}