package vinistef.clinic_api.service.patientService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vinistef.clinic_api.dto.*;

public interface IPatientService {
    PatientRegisterResponseDto registerPatient(RegisterPatientDto patient);
    Page<PatientDetailsDto> getAllPatients(Pageable pageable);
    PatientDetailsDto getPatientDetails(String id);
    PatientDetailsDto updatePatient(String id, UpdatePatientDto patient);
    void deletePatient(String id);
}
