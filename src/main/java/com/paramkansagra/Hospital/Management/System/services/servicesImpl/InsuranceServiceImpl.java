package com.paramkansagra.Hospital.Management.System.services.servicesImpl;

import com.paramkansagra.Hospital.Management.System.entity.Insurance;
import com.paramkansagra.Hospital.Management.System.entity.Patient;
import com.paramkansagra.Hospital.Management.System.repository.InsuranceRepository;
import com.paramkansagra.Hospital.Management.System.repository.PatientRepository;
import com.paramkansagra.Hospital.Management.System.services.InsuranceService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {

    public final ModelMapper modelMapper;
    public final InsuranceRepository insuranceRepository;
    public final PatientRepository patientRepository;

    @Transactional // either all the operations are performed or none of them are performed
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException(String.format("Patient with id -> %d not found", patientId)));

        patient.setInsurance(insurance); // it will set the insurance and will also bring the insurance id and update the object
        insurance.setPatient(patient); // bidirectional consistency maintained

        // patient.setInsurance will insert the object in the db still doing this
        patientRepository.save(patient);
        insuranceRepository.save(insurance);

        return patient;
    }

    @Transactional
    public void disassociateInsuranceFromPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException(String.format("Patient with id : %d not found", patientId)));

        // Also because insurance doesn't have anymore parents and orphan = true it would be removed automatically
        patient.setInsurance(null); // this will set the insurance as null and make the patient dirty thus will be saved automatically
    }
}
