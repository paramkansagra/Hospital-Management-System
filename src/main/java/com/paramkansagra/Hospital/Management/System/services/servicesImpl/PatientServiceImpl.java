package com.paramkansagra.Hospital.Management.System.services.servicesImpl;

import com.paramkansagra.Hospital.Management.System.dto.PatientDTO;
import com.paramkansagra.Hospital.Management.System.entity.Patient;
import com.paramkansagra.Hospital.Management.System.repository.PatientRepository;
import com.paramkansagra.Hospital.Management.System.services.PatientService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    public PatientServiceImpl(PatientRepository patientRepository , ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public PatientDTO getPatientById(Long id) {
        Patient p1 = patientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Patient with id -> " + id + " not found"));

        return modelMapper.map(p1 , PatientDTO.class);
    }
}
