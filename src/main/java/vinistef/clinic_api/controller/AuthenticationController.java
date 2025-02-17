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
import vinistef.clinic_api.dto.JwtTokenDataDto;
import vinistef.clinic_api.entity.User;
import vinistef.clinic_api.service.tokenService.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<JwtTokenDataDto> authenticate(@RequestBody @Valid AuthenticationDto authenticationDto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(authenticationDto.login(), authenticationDto.password());
        var authentication = authenticationManager.authenticate(authenticationToken);

        String jwtToken = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new JwtTokenDataDto(jwtToken));
    }
}
