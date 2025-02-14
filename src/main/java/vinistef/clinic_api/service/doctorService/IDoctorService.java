package vinistef.clinic_api.service.doctorService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vinistef.clinic_api.dto.*;

public interface IDoctorService {

    DoctorRegisterResponseDto registerDoctor(RegisterDoctorDto doctor);
    Page<DoctorDetailsWithAddressDto> getAllDoctors(Pageable pageable);
    DoctorDetailsDto getDoctorDetails(String id);
    DoctorDetailsDto updateDoctor(String id, UpdateDoctorDto doctor);
    void deleteDoctor(String id);
}
