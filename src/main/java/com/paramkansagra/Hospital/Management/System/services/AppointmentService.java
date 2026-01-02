package com.paramkansagra.Hospital.Management.System.services;

import com.paramkansagra.Hospital.Management.System.entity.Appointment;
import org.springframework.stereotype.Service;

@Service
public interface AppointmentService {
    public void createNewAppointment(Appointment appointment , Long doctorId , Long patientId);
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointment_id , Long doctor_id);
}
