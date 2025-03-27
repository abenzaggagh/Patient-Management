package com.abenzaggagh.analyticsservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

import java.util.Arrays;

@Slf4j

@Service
public class KafkaConsumer {


    @KafkaListener(topics = "patient", groupId = "analytics-service")
    public void consumeEvent(ConsumerRecord<String, byte[]> record) {
        log.info("Received record with key : {}", record.key());
        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(record.value());

            log.info("Received Patient event : {}", patientEvent);

        } catch (InvalidProtocolBufferException e) {
            log.error("Error parsing Patient {}", Arrays.toString(record.value()));
        }
    }

}
