package co.inventorsoft.warehouse.security.service.impl;

import co.inventorsoft.warehouse.security.dto.JwtRequestDto;
import co.inventorsoft.warehouse.security.dto.JwtResponseDto;
import co.inventorsoft.warehouse.security.service.AuthService;
import co.inventorsoft.warehouse.security.service.UserService;
import co.inventorsoft.warehouse.security.utils.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtTokenManager jwtTokenManager;

    public ResponseEntity<?> generateJwtToken(@RequestBody JwtRequestDto authRequest) {
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        if (!authRequest.getPassword().equals(userDetails.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }
        String token = jwtTokenManager.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponseDto(token));
    }
}
