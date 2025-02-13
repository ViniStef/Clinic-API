package vinistef.clinic_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDataDto(
        @NotBlank
        String street,

        String number,
        String complement,

        @NotBlank
        String neighborhood,

        @NotBlank

        @NotBlank
        String city,

        @NotBlank
        String state,

        @NotBlank
        @Pattern(regexp = "\\d{8}")
        @JsonProperty("zip_code") String zipCode
) {
}
