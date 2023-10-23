package co.inventorsoft.warehouse.security.service.impl;

import co.inventorsoft.warehouse.security.entity.RoleEntity;
import co.inventorsoft.warehouse.security.repository.RoleRepository;
import co.inventorsoft.warehouse.security.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleEntity getUserRole() {
        return roleRepository.findByName("ROLE_USER").orElseThrow();
    }
}
