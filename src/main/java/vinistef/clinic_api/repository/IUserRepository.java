package vinistef.clinic_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vinistef.clinic_api.entity.User;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
