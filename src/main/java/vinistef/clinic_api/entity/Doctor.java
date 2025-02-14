package vinistef.clinic_api.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import vinistef.clinic_api.dto.RegisterDoctorDto;
import vinistef.clinic_api.dto.UpdateDoctorDto;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String cellphone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    private Boolean active;

    public Doctor(RegisterDoctorDto registerDoctorDto) {
        this.name = registerDoctorDto.name();
        this.email = registerDoctorDto.email();
        this.cellphone = registerDoctorDto.cellphone();
        this.crm = registerDoctorDto.crm();
        this.specialty = registerDoctorDto.specialty();
        this.address = new Address(registerDoctorDto.address());
        this.active = true;
    }

    public void updateData(@Valid UpdateDoctorDto doctorUpdateData) {
        if (doctorUpdateData.name() != null) {
            this.name = doctorUpdateData.name();
        }
        if (doctorUpdateData.cellphone() != null) {
            this.cellphone = doctorUpdateData.cellphone();
        }
        if (doctorUpdateData.addressDataDto() != null) {
            this.address.updateAddress(doctorUpdateData.addressDataDto());
        }
    }

    public void delete() {
        this.setActive(false);
    }
}
