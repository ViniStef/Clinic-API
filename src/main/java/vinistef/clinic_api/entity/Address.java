package vinistef.clinic_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vinistef.clinic_api.dto.AddressDataDto;

@Embeddable
@Getter
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

    public Address() {

    }

    public Address(AddressDataDto addressDataDto) {
        this.street = addressDataDto.street();
        this.number = addressDataDto.number();
        this.complement = addressDataDto.complement();
        this.neighborhood = addressDataDto.neighborhood();
        this.city = addressDataDto.city();
        this.state = addressDataDto.state();
        this.zipCode = addressDataDto.zipCode();
    }

}
