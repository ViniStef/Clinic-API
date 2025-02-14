package vinistef.clinic_api.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vinistef.clinic_api.dto.*;
import vinistef.clinic_api.service.patientService.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<PatientDetailsDto> register(@RequestBody @Valid RegisterPatientDto registerPatientDto, UriComponentsBuilder uriComponentsBuilder) {
        PatientRegisterResponseDto patientRegisterResponseDto = patientService.registerPatient(registerPatientDto);
        var uri = uriComponentsBuilder.path("/doctor/{id}").buildAndExpand(patientRegisterResponseDto.id()).toUri();

        return ResponseEntity.created(uri).body(patientRegisterResponseDto.patientDetailsDto());
    }

    @GetMapping
    public ResponseEntity<Page<PatientDetailsDto>> getAll(Pageable pageable) {
        return ResponseEntity.status(200).body(patientService.getAllPatients(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDetailsDto> getDetails(@PathVariable String id) {
        PatientDetailsDto patientDetails = patientService.getPatientDetails(id);

        return ResponseEntity.status(200).body(patientDetails);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDetailsDto> update(@PathVariable String id, @RequestBody @Valid UpdatePatientDto patientUpdateData) {
        PatientDetailsDto patientDetails = patientService.updatePatient(id, patientUpdateData);

        return ResponseEntity.status(200).body(patientDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        patientService.deletePatient(id);

        return ResponseEntity.status(204).build();
    }
}
