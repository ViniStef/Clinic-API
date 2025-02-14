package vinistef.clinic_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import vinistef.clinic_api.entity.User;

public interface IAuthenticationRepository extends JpaRepository<User, Long> {

    UserDetails findByLogin(String login);
}
