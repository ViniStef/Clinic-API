package vinistef.clinic_api.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import vinistef.clinic_api.dto.DoctorRegisterDto;
import vinistef.clinic_api.dto.DoctorUpdateData;

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

    public Doctor(DoctorRegisterDto doctorRegisterDto) {
        this.name = doctorRegisterDto.name();
        this.email = doctorRegisterDto.email();
        this.cellphone = doctorRegisterDto.cellphone();
        this.crm = doctorRegisterDto.crm();
        this.specialty = doctorRegisterDto.specialty();
        this.address = new Address(doctorRegisterDto.address());
        this.active = true;
    }

    public void updateData(@Valid DoctorUpdateData doctorUpdateData) {
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
