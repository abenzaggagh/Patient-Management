package com.abenzaggagh.patientservice.service;

import com.abenzaggagh.patientservice.dto.PatientRequestDTO;
import com.abenzaggagh.patientservice.dto.PatientResponseDTO;
import com.abenzaggagh.patientservice.mapper.PatientMapper;
import com.abenzaggagh.patientservice.model.Patient;
import com.abenzaggagh.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public List<PatientResponseDTO> getPatients() {
        return patientRepository.findAll()
                .stream()
                .map(PatientMapper::toDTO)
                .toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.save(PatientMapper.toEntity(patientRequestDTO));

        return PatientMapper.toDTO(patient);
    }

}
