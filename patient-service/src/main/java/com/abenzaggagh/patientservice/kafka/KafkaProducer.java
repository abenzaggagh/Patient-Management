package com.abenzaggagh.patientservice.kafka;

import com.abenzaggagh.patientservice.model.Patient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Slf4j
@RequiredArgsConstructor

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public void sendEvent(Patient patient, String eventType) {
        PatientEvent patientEvent = PatientEvent
                .newBuilder()
                .setPatientId(patient.getId().toString())
                .setName(patient.getName())
                .setEmail(patient.getEmail())
                .setEventType(eventType)
                .build();

        try {
            kafkaTemplate.send("patient", patient.getId().toString(), patientEvent.toByteArray());
        } catch (Exception exception) {
            log.error("Error sending Patient " + eventType + " event " + exception.getMessage());
        }
    }

}
