package vinistef.clinic_api.dto;

import vinistef.clinic_api.entity.Address;
import vinistef.clinic_api.entity.Doctor;
import vinistef.clinic_api.entity.Specialty;

public record DoctorDetailsDto(String name, String email, String crm, String cellphone, Specialty specialty, Address address) {

    public DoctorDetailsDto(Doctor doctor) {
        this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getCellphone(), doctor.getSpecialty(), doctor.getAddress());
    }
}
