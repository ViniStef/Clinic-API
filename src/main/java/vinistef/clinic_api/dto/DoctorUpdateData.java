package vinistef.clinic_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DoctorUpdateData(
        String name,
        String cellphone,
        AddressDataDto addressDataDto) {
}
