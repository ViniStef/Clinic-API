package vinistef.clinic_api.dto;

import vinistef.clinic_api.entity.Doctor;
import vinistef.clinic_api.entity.Specialty;

public record DoctorDetailsWithAddressDto(Long id, String name, String email, String crm, Specialty specialty) {

    public DoctorDetailsWithAddressDto(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
