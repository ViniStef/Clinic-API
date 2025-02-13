package vinistef.clinic_api.dto;

import vinistef.clinic_api.entity.Doctor;
import vinistef.clinic_api.entity.Specialty;

public record GetDoctorDataDto(Long id, String name, String email, String crm, Specialty specialty) {

    public GetDoctorDataDto(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
