package vinistef.clinic_api.controller;

import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vinistef.clinic_api.dto.DoctorRegisterDto;
import vinistef.clinic_api.entity.Doctor;
import vinistef.clinic_api.repository.DoctorRepository;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorRepository doctorRepository;

    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid DoctorRegisterDto doctorRegisterDto) {
        doctorRepository.save(new Doctor(doctorRegisterDto));
    }
}
