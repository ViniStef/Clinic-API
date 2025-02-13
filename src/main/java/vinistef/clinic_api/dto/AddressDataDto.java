package vinistef.clinic_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

public record AddressDataDto(String street, String number, String complement, String neighborhood, String city, String state, @JsonProperty("zip_code") String zipCode) {
}
