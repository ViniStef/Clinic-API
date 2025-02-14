package vinistef.clinic_api.dto;

import vinistef.clinic_api.entity.Address;
import vinistef.clinic_api.entity.Patient;

public record PatientDetailsDto(String name, String email, String cellphone, String cpf, Address address) {

    public PatientDetailsDto(Patient patient) {
        this(patient.getName(), patient.getEmail(), patient.getCellphone(), patient.getCpf(), patient.getAddress());
    }
}
