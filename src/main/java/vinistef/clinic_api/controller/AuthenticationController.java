package vinistef.clinic_api.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vinistef.clinic_api.dto.AuthenticationDto;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    public AuthenticationController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<Void> authenticate(@RequestBody @Valid AuthenticationDto authenticationDto) {
        var token = new UsernamePasswordAuthenticationToken(authenticationDto.login(), authenticationDto.password());
        var authentication = authenticationManager.authenticate(token);

        return ResponseEntity.ok().build();
    }
}
