package com.paramkansagra.Hospital.Management.System.controller;

import com.paramkansagra.Hospital.Management.System.dto.DoctorDTO;
import com.paramkansagra.Hospital.Management.System.repository.DoctorRepository;
import com.paramkansagra.Hospital.Management.System.services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/public")
public class HospitalController {
    private final DoctorService doctorService;

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors(){
        return ResponseEntity.status(200).body(doctorService.getAllDoctors());
    }
}
