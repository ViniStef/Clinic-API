package vinistef.clinic_api.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import vinistef.clinic_api.dto.DoctorUpdateData;
import vinistef.clinic_api.dto.GetDoctorDataDto;
import vinistef.clinic_api.dto.DoctorRegisterDto;
import vinistef.clinic_api.entity.Doctor;
import vinistef.clinic_api.repository.DoctorRepository;

import java.util.List;
import java.util.stream.Stream;

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

    @GetMapping
    public ResponseEntity<Page<GetDoctorDataDto>> getAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return ResponseEntity.ok((doctorRepository.findAllByActiveTrue(pageable).map(GetDoctorDataDto::new)));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<GetDoctorDataDto> update(@PathVariable String id, @RequestBody @Valid DoctorUpdateData doctorUpdateData) {
        Doctor doctor = doctorRepository.findById(Long.parseLong(id)).orElseThrow();
        doctor.updateData(doctorUpdateData);
        return ResponseEntity.ok().body(new GetDoctorDataDto(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable String id) {
        Doctor doctorToDelete = doctorRepository.findById(Long.parseLong(id)).orElseThrow();
        doctorToDelete.delete();
        return ResponseEntity.status(204).build();
    }
}
