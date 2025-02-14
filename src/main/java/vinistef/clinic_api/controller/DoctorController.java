package vinistef.clinic_api.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vinistef.clinic_api.dto.DoctorDetailsDto;
import vinistef.clinic_api.dto.DoctorUpdateData;
import vinistef.clinic_api.dto.GetDoctorDataDto;
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
    public ResponseEntity<DoctorDetailsDto> register(@RequestBody @Valid DoctorRegisterDto doctorRegisterDto, UriComponentsBuilder uriComponentsBuilder) {
        Doctor doctor = new Doctor(doctorRegisterDto);
        doctorRepository.save(doctor);

        var uri = uriComponentsBuilder.path("/doctor/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DoctorDetailsDto(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<GetDoctorDataDto>> getAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        Page<GetDoctorDataDto> doctors = doctorRepository.findAllByActiveTrue(pageable).map(GetDoctorDataDto::new);
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDetailsDto> getDetails(@PathVariable String id) {
        Doctor doctor = doctorRepository.findById(Long.parseLong(id)).orElseThrow();
        return ResponseEntity.status(200).body(new DoctorDetailsDto(doctor));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DoctorDetailsDto> update(@PathVariable String id, @RequestBody @Valid DoctorUpdateData doctorUpdateData) {
        Doctor doctor = doctorRepository.findById(Long.parseLong(id)).orElseThrow();
        doctor.updateData(doctorUpdateData);
        return ResponseEntity.ok().body(new DoctorDetailsDto(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable String id) {
        Doctor doctorToDelete = doctorRepository.findById(Long.parseLong(id)).orElseThrow();
        doctorToDelete.delete();
        return ResponseEntity.status(204).build();
    }
}
