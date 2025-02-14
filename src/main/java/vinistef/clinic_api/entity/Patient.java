package vinistef.clinic_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vinistef.clinic_api.dto.RegisterPatientDto;
import vinistef.clinic_api.dto.UpdatePatientDto;

@Entity(name = "Patient")
@Table(name = "patients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String cellphone;
    private String cpf;
    private Address address;
    private Boolean active;

    public Patient(RegisterPatientDto patient) {
        this.name = patient.name();
        this.email = patient.email();
        this.cellphone = patient.cellphone();
        this.cpf = patient.cpf();
        this.address = new Address(patient.addressDto());
        this.active = true;
    }

    public void updatePatient(UpdatePatientDto updatePatientDto) {
        if (updatePatientDto.name() != null) {
            this.name = updatePatientDto.name();
        }
        if (updatePatientDto.cellphone() != null) {
            this.cellphone = updatePatientDto.cellphone();
        }
        if (updatePatientDto.addressDto() != null) {
            this.address.updateAddress(updatePatientDto.addressDto());
        }
    }
}
