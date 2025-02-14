package vinistef.clinic_api.dto;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegisterPatientDto(
        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "\\d{9,11}")
        String cellphone,

        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,

        @Embedded
        AddressDataDto addressDto) {
}
