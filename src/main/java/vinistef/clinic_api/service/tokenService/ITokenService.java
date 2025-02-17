package vinistef.clinic_api.service.tokenService;

import vinistef.clinic_api.entity.User;

public interface ITokenService {
    String generateToken(User user);
}
