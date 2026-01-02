package com.paramkansagra.Hospital.Management.System.services;

import com.paramkansagra.Hospital.Management.System.dto.AppointmentDTO;
import com.paramkansagra.Hospital.Management.System.entity.Appointment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppointmentService {
    public void createNewAppointment(Appointment appointment , Long doctorId , Long patientId);
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointment_id , Long doctor_id);
    public List<AppointmentDTO> getAllAppointments();
}
