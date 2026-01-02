package com.paramkansagra.Hospital.Management.System.services;

import com.paramkansagra.Hospital.Management.System.dto.PatientDTO;
import com.paramkansagra.Hospital.Management.System.entity.Insurance;
import com.paramkansagra.Hospital.Management.System.entity.Patient;
import com.paramkansagra.Hospital.Management.System.repository.InsuranceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface InsuranceService {
    public Patient assignInsuranceToPatient(Insurance insurance , Long patientId);
}
