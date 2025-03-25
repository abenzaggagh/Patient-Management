package com.abenzaggagh.patientservice.mapper;

import com.abenzaggagh.patientservice.dto.PatientRequestDTO;
import com.abenzaggagh.patientservice.dto.PatientResponseDTO;
import com.abenzaggagh.patientservice.model.Patient;

import java.time.LocalDate;
import java.util.UUID;

public class PatientMapper {

    public static PatientResponseDTO toDTO(Patient patient) {
        PatientResponseDTO patientDTO = new PatientResponseDTO();

        patientDTO.setId(patient.getId().toString());
        patientDTO.setName(patient.getName());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());

        return patientDTO;
    }

    public static Patient toEntity(PatientRequestDTO patientDTO) {
        Patient patient = new Patient();

        patient.setName(patientDTO.getName());
        patient.setEmail(patientDTO.getEmail());
        patient.setAddress(patientDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientDTO.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.now());

        return patient;
    }

}
