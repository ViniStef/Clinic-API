package vinistef.clinic_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vinistef.clinic_api.entity.Doctor;
import vinistef.clinic_api.entity.Patient;

public interface IPatientRepository extends JpaRepository<Patient, Long> {

    Page<Patient> findAllByActiveTrue(Pageable pageable);
}
