package co.inventorsoft.warehouse.security.service;

import co.inventorsoft.warehouse.security.dto.JwtRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface AuthService {
    ResponseEntity<?> generateJwtToken(@RequestBody JwtRequestDto authRequest);
}
