package com.paramkansagra.Hospital.Management.System.services.servicesImpl;

import com.paramkansagra.Hospital.Management.System.dto.AppointmentDTO;
import com.paramkansagra.Hospital.Management.System.entity.Appointment;
import com.paramkansagra.Hospital.Management.System.entity.Doctor;
import com.paramkansagra.Hospital.Management.System.entity.Patient;
import com.paramkansagra.Hospital.Management.System.repository.AppointmentRepository;
import com.paramkansagra.Hospital.Management.System.repository.DoctorRepository;
import com.paramkansagra.Hospital.Management.System.repository.PatientRepository;
import com.paramkansagra.Hospital.Management.System.services.AppointmentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void createNewAppointment(Appointment appointment, Long doctorId, Long patientId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new EntityNotFoundException(String.format("Doctor with id : %d not found" , doctorId)));
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException(String.format("Patient with id : %d not found" , patientId)));

        if(appointment.getId() != null){
            throw new IllegalArgumentException("Appointment should not have an appointment id");
        }

        // because appointment is the owning class owning patient and doctor
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        // to maintain bi-directional mapping
        patient.getAppointments().add(appointment);
        doctor.getAppointment().add(appointment);

        appointmentRepository.save(appointment);
    }

    @Override
    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointment_id, Long doctor_id) {
        Appointment appointment = appointmentRepository.findById(appointment_id).orElseThrow(() -> new EntityNotFoundException(String.format("Appointment with id : %d not found" , appointment_id)));
        Doctor doctor = doctorRepository.findById(doctor_id).orElseThrow(() -> new EntityNotFoundException(String.format("Doctor with id : %d not found" , doctor_id)));

        if(appointment.getDoctor() == doctor){
            throw new IllegalArgumentException("Cannot assign the appointment to the same doctor");
        }

        appointment.setDoctor(doctor); // this will automatically call update because it is dirty
        doctor.getAppointment().add(appointment); // to have bi-directional mapping

        doctorRepository.save(doctor);

        return appointment;
    }

    @Override
    public List<AppointmentDTO> getAllAppointments(){
        return appointmentRepository.findAll().stream().map((element) -> modelMapper.map(element, AppointmentDTO.class)).collect(Collectors.toList());
    }
}
