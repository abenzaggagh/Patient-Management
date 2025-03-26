package com.abenzaggagh.patientservice.service;

import com.abenzaggagh.patientservice.dto.PatientRequestDTO;
import com.abenzaggagh.patientservice.dto.PatientResponseDTO;
import com.abenzaggagh.patientservice.exception.BusinessException;
import com.abenzaggagh.patientservice.mapper.PatientMapper;
import com.abenzaggagh.patientservice.model.Patient;
import com.abenzaggagh.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;


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

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO, Locale locale) {
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new BusinessException("email.exists", locale, patientRequestDTO.getEmail());
        }

        Patient patient = patientRepository.save(PatientMapper.toEntity(patientRequestDTO));

        return PatientMapper.toDTO(patient);
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO, Locale locale) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new BusinessException("patient.not.found", locale, id));

        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
            throw new BusinessException("email.exists", locale, patientRequestDTO.getEmail());
        }

        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        return PatientMapper.toDTO(patientRepository.save(patient));
    }

    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }

}
