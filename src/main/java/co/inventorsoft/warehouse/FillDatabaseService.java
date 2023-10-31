package co.inventorsoft.warehouse;

import co.inventorsoft.warehouse.domain.entity.ManufacturerEntity;
import co.inventorsoft.warehouse.domain.entity.ShoeEntity;
import co.inventorsoft.warehouse.domain.entity.SizeEntity;
import co.inventorsoft.warehouse.domain.repository.ManufacturerRepository;
import co.inventorsoft.warehouse.domain.repository.ShoeRepository;
import co.inventorsoft.warehouse.domain.repository.SizeRepository;
import co.inventorsoft.warehouse.security.entity.RoleEntity;
import co.inventorsoft.warehouse.security.entity.UserEntity;
import co.inventorsoft.warehouse.security.repository.RoleRepository;
import co.inventorsoft.warehouse.security.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class FillDatabaseService {

    private final ManufacturerRepository manufacturerRepository;
    private final ShoeRepository shoeRepository;
    private final SizeRepository sizeRepository;

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public static ManufacturerEntity createManufacturer(long id, String name) {
        return ManufacturerEntity.builder()
                .id(id)
                .name(name)
                .build();
    }

    public static SizeEntity createSize(long id, int size, int stock) {
        return SizeEntity.builder()
                .id(id)
                .size(size)
                .stock(stock)
                .build();
    }

    public static ShoeEntity createShoe(String type, ManufacturerEntity manufacturer, SizeEntity size) {
        return ShoeEntity.builder()
                .type(type)
                .manufacturer(manufacturer)
                .size(size)
                .build();
    }

    @PostConstruct
    public void generateData() {
        generateDomainData();
        generateUserData();
    }

    private void generateDomainData() {
        ManufacturerEntity nike = createManufacturer(1L, "Nike");
        ManufacturerEntity adidas = createManufacturer(2L, "Adidas");
        ManufacturerEntity puma = createManufacturer(3L, "Puma");


        SizeEntity size40 = createSize(1L, 40, 100);
        SizeEntity size41 = createSize(2L, 41, 150);
        SizeEntity size42 = createSize(3L, 42, 75);

        ShoeEntity runningShoe = createShoe("Running Shoe", nike, size40);
        ShoeEntity casualShoe = createShoe("Casual Shoe", adidas, size41);
        ShoeEntity sportsShoe = createShoe("Sports Shoe", puma, size42);

        saveEntities(runningShoe, casualShoe, sportsShoe);
        saveEntities(nike, adidas, puma);
        saveEntities(size40, size41, size42);

        ManufacturerEntity nike2 = createManufacturer(10L, "Nike10");
        ManufacturerEntity adidas2 = createManufacturer(11L, "Adidas11");
        ManufacturerEntity puma2 = createManufacturer(12L, "Puma12");

        SizeEntity size4011 = createSize(10L, 40, 100);
        SizeEntity size4012 = createSize(11L, 41, 150);
        SizeEntity size4013 = createSize(12L, 42, 75);

        saveEntities(nike2, adidas2, puma2);
        saveEntities(size4011, size4012, size4013);

    }

    private void generateUserData() {
        RoleEntity roleUser1 = RoleEntity.builder()
                .name("ROLE_USER")
                .build();

        RoleEntity roleAdmin = RoleEntity.builder()
                .name("ROLE_ADMIN")
                .build();
        Collection<RoleEntity> roleEntityCollection1 = List.of(roleUser1);
        Collection<RoleEntity> roleEntityCollection2 = List.of(roleAdmin);

        UserEntity user1 = UserEntity.builder()
                .username("user1")
                .email("user1@email.com")
                .password("user1password")
                .roleEntity(roleEntityCollection1)
                .build();

        UserEntity admin = UserEntity.builder()
                .username("admin")
                .email("admin@email.com")
                .password("adminpassword")
                .roleEntity(roleEntityCollection2)
                .build();


        saveEntities(roleUser1, roleAdmin);
        saveEntities(user1, admin);


    }

    private void saveEntities(ManufacturerEntity... manufacturers) {
        manufacturerRepository.saveAll(Arrays.asList(manufacturers));
    }

    private void saveEntities(SizeEntity... sizes) {
        sizeRepository.saveAll(Arrays.asList(sizes));
    }

    private void saveEntities(ShoeEntity... shoes) {
        shoeRepository.saveAll(Arrays.asList(shoes));
    }

    private void saveEntities(RoleEntity... roles) {
        roleRepository.saveAll(Arrays.asList(roles));
    }

    private void saveEntities(UserEntity... users) {
        userRepository.saveAll(Arrays.asList(users));
    }


}
