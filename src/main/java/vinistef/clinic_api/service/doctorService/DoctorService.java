package vinistef.clinic_api.service.doctorService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vinistef.clinic_api.dto.*;
import vinistef.clinic_api.entity.Doctor;
import vinistef.clinic_api.repository.IDoctorRepository;

@Service
public class DoctorService implements IDoctorService {

    private final IDoctorRepository doctorRepository;

    public DoctorService(IDoctorRepository IDoctorRepository) {
        this.doctorRepository = IDoctorRepository;
    }

    @Override
    public DoctorRegisterResponseDto registerDoctor(RegisterDoctorDto doctor) {
        Doctor newDoctor = new Doctor(doctor);
        doctorRepository.save(newDoctor);
        return new DoctorRegisterResponseDto(newDoctor.getId(), new DoctorDetailsDto(newDoctor));
    }

    @Override
    public Page<DoctorDetailsWithAddressDto> getAllDoctors(Pageable pageable) {
        return doctorRepository.findAllByActiveTrue(pageable).map(DoctorDetailsWithAddressDto::new);
    }

    @Override
    public DoctorDetailsDto getDoctorDetails(String id) {
        var doctorFound = doctorRepository.findById(Long.parseLong(id)).orElseThrow();
        return new DoctorDetailsDto(doctorFound);
    }

    @Override
    public DoctorDetailsDto updateDoctor(String id, UpdateDoctorDto doctor) {
        Doctor doctorFound = doctorRepository.findById(Long.parseLong(id)).orElseThrow();
        doctorFound.updateData(doctor);
        return new DoctorDetailsDto(doctorFound);
    }

    @Override
    public void deleteDoctor(String id) {
        Doctor doctorToDelete = doctorRepository.findById(Long.parseLong(id)).orElseThrow();
        doctorToDelete.delete();
    }
}
