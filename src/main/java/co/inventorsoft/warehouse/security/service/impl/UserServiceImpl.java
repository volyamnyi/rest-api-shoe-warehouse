package co.inventorsoft.warehouse.security.service.impl;

import co.inventorsoft.warehouse.security.entity.UserEntity;
import co.inventorsoft.warehouse.security.repository.UserRepository;
import co.inventorsoft.warehouse.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " does not found"));
        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getRoleEntity().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList())
        );
    }
}
