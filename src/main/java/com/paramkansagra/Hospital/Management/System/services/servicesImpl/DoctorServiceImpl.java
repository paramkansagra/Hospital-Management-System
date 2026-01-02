package com.paramkansagra.Hospital.Management.System.services.servicesImpl;

import com.paramkansagra.Hospital.Management.System.dto.DoctorDTO;
import com.paramkansagra.Hospital.Management.System.repository.DoctorRepository;
import com.paramkansagra.Hospital.Management.System.services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream().map(doctor -> modelMapper.map(doctor , DoctorDTO.class)).toList();
    }
}
