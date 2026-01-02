package com.paramkansagra.Hospital.Management.System.controller;

import com.paramkansagra.Hospital.Management.System.dto.AppointmentDTO;
import com.paramkansagra.Hospital.Management.System.repository.AppointmentRepository;
import com.paramkansagra.Hospital.Management.System.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {

    private final AppointmentService appointmentService;

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointmentsOfDoctor() {
        return ResponseEntity.status(200).body(appointmentService.getAllAppointments());
    }
}
