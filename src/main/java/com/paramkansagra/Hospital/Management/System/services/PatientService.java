package com.paramkansagra.Hospital.Management.System.services;

import com.paramkansagra.Hospital.Management.System.dto.PatientDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {
    public PatientDTO getPatientById(Long id);
    public List<PatientDTO> getPatientByName(String name);
    public List<PatientDTO> getPatientByNameOrEmail(String name , String email);
    public List<PatientDTO> getAllPatients(Integer pageNumber , Integer pageSize);
}
