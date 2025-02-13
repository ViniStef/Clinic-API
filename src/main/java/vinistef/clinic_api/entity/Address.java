package vinistef.clinic_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vinistef.clinic_api.dto.AddressDataDto;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    @Column(name = "zip_code")
    private String zipCode;

    public Address(AddressDataDto addressDataDto) {
        this.street = addressDataDto.street();
        this.number = addressDataDto.number();
        this.complement = addressDataDto.complement();
        this.neighborhood = addressDataDto.neighborhood();
        this.city = addressDataDto.city();
        this.state = addressDataDto.state();
        this.zipCode = addressDataDto.zipCode();
    }

    public void updateAddress(AddressDataDto addressDataDto) {
        if (addressDataDto.street() != null) {
            this.street = addressDataDto.street();
        }
        if (addressDataDto.number() != null) {
            this.number = addressDataDto.number();
        }
        if (addressDataDto.complement() != null) {
            this.complement = addressDataDto.complement();
        }
        if (addressDataDto.neighborhood() != null) {
            this.neighborhood = addressDataDto.neighborhood();
        }
        if (addressDataDto.city() != null) {
            this.city = addressDataDto.city();
        }
        if (addressDataDto.state() != null) {
            this.state = addressDataDto.state();
        }
        if (addressDataDto.zipCode() != null) {
            this.zipCode = addressDataDto.zipCode();
        }
    }
}
