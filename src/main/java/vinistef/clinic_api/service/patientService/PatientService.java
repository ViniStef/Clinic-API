package vinistef.clinic_api.service.patientService;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vinistef.clinic_api.dto.PatientDetailsDto;
import vinistef.clinic_api.dto.PatientRegisterResponseDto;
import vinistef.clinic_api.dto.RegisterPatientDto;
import vinistef.clinic_api.dto.UpdatePatientDto;
import vinistef.clinic_api.entity.Patient;
import vinistef.clinic_api.repository.IPatientRepository;

@Service
public class PatientService implements  IPatientService {

    private final IPatientRepository patientRepository;

    public PatientService(IPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional
    public PatientRegisterResponseDto registerPatient(RegisterPatientDto patient) {
        Patient newPatient = new Patient(patient);
        patientRepository.save(newPatient);

        return new PatientRegisterResponseDto(newPatient.getId(), new PatientDetailsDto(newPatient));
    }

    @Override
    public Page<PatientDetailsDto> getAllPatients(Pageable pageable) {
        return patientRepository.findAllByActiveTrue(pageable).map(PatientDetailsDto::new);
    }

    @Override
    public PatientDetailsDto getPatientDetails(String id) {
    Patient patientFound = patientRepository.findById(Long.parseLong(id)).orElseThrow();
    return new PatientDetailsDto(patientFound);
    }

    @Override
    @Transactional
    public PatientDetailsDto updatePatient(String id, UpdatePatientDto patient) {
        Patient patientFound = patientRepository.findById(Long.parseLong(id)).orElseThrow();
        patientFound.updatePatient(patient);

        return new PatientDetailsDto(patientFound);
    }

    @Override
    @Transactional
    public void deletePatient(String id) {
        Patient patientFound = patientRepository.findById(Long.parseLong(id)).orElseThrow();
        patientRepository.delete(patientFound);
    }


}
