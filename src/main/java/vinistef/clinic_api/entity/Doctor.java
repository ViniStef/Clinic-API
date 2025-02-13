package vinistef.clinic_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vinistef.clinic_api.dto.DoctorRegisterDto;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    public Doctor(DoctorRegisterDto doctorRegisterDto) {
        this.name = doctorRegisterDto.name();
        this.email = doctorRegisterDto.email();
        this.cellphone = doctorRegisterDto.cellphone();
        this.crm = doctorRegisterDto.crm();
        this.specialty = doctorRegisterDto.specialty();
        this.address = new Address(doctorRegisterDto.address());
    }

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

}
