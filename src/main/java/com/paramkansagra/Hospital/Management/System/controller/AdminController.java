package com.paramkansagra.Hospital.Management.System.controller;

import com.paramkansagra.Hospital.Management.System.dto.PatientDTO;
import com.paramkansagra.Hospital.Management.System.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final PatientService patientService;

    @GetMapping("/patients")
    public ResponseEntity<List<PatientDTO>> getAllPatients(@RequestParam(value = "page", defaultValue = "0") Integer pageNumber, @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        return ResponseEntity.status(200).body(patientService.getAllPatients(pageNumber, pageSize));
    }
}
