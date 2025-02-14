package vinistef.clinic_api.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vinistef.clinic_api.dto.*;
import vinistef.clinic_api.service.doctorService.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DoctorDetailsDto> register(@RequestBody @Valid RegisterDoctorDto registerDoctorDto, UriComponentsBuilder uriComponentsBuilder) {
        DoctorRegisterResponseDto doctorRegisterResponse = doctorService.registerDoctor(registerDoctorDto);
        var uri = uriComponentsBuilder.path("/doctor/{id}").buildAndExpand(doctorRegisterResponse.id()).toUri();

        return ResponseEntity.created(uri).body(doctorRegisterResponse.doctorDetailsDto());
    }

    @GetMapping
    public ResponseEntity<Page<DoctorDetailsWithAddressDto>> getAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        Page<DoctorDetailsWithAddressDto> allDoctors = doctorService.getAllDoctors(pageable);

        return ResponseEntity.status(200).body(allDoctors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDetailsDto> getDetails(@PathVariable String id) {
        DoctorDetailsDto doctorDetails = doctorService.getDoctorDetails(id);

        return ResponseEntity.status(200).body(doctorDetails);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DoctorDetailsDto> update(@PathVariable String id, @RequestBody @Valid UpdateDoctorDto doctorUpdateData) {
        DoctorDetailsDto doctorDetails = doctorService.updateDoctor(id, doctorUpdateData);

        return ResponseEntity.ok().body(doctorDetails);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable String id) {
        doctorService.deleteDoctor(id);

        return ResponseEntity.status(204).build();
    }
}
