package vinistef.clinic_api.dto;

import vinistef.clinic_api.entity.Address;
import vinistef.clinic_api.entity.Doctor;
import vinistef.clinic_api.entity.Specialty;

public record DoctorRegisterDto(String name, String email, String crm, Specialty specialty, AddressDataDto address){


}
