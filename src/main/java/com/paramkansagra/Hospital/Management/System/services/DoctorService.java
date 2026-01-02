package com.paramkansagra.Hospital.Management.System.services;

import com.paramkansagra.Hospital.Management.System.dto.AppointmentDTO;
import com.paramkansagra.Hospital.Management.System.dto.DoctorDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DoctorService {
    public List<DoctorDTO> getAllDoctors();
}
