package com.abenzaggagh.patientservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PatientRequestDTO {

    @NotBlank(message = "{name.required}")
    @Size(max = 100, message = "{name.size}")
    private String name;

    @NotBlank(message = "{email.required}")
    @Email(message = "{email.valid}")
    private String email;

    @NotBlank(message = "{address.required}")
    private String address;

    @NotBlank(message = "{dateOfBirth.required}")
    private String dateOfBirth;

}
