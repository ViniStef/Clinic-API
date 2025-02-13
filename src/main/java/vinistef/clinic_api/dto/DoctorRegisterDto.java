package vinistef.clinic_api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import vinistef.clinic_api.entity.Specialty;

public record DoctorRegisterDto(
        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "\\d{9,11}")
        String cellphone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull
        Specialty specialty,

        @NotNull @Valid
        AddressDataDto address
){


}
