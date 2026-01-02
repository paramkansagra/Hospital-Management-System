package com.paramkansagra.Hospital.Management.System.controller;

import com.paramkansagra.Hospital.Management.System.dto.PatientDTO;
import com.paramkansagra.Hospital.Management.System.entity.Patient;
import com.paramkansagra.Hospital.Management.System.services.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {


    private final PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    public PatientDTO getPatientById(@PathVariable Long id){
        return patientService.getPatientById(id);
    }

    @GetMapping("/name={name}")
    public List<PatientDTO> getPatientByName(@PathVariable String name){
        return patientService.getPatientByName(name);
    }
}
