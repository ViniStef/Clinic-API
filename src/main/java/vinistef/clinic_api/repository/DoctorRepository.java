package vinistef.clinic_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vinistef.clinic_api.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
