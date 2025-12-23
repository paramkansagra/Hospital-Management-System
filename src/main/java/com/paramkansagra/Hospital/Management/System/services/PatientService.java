package com.paramkansagra.Hospital.Management.System.services;

import com.paramkansagra.Hospital.Management.System.dto.PatientDTO;
import org.springframework.stereotype.Service;

@Service
public interface PatientService {
    public PatientDTO getPatientById(Long id);
}
