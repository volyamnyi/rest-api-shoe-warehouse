package co.inventorsoft.warehouse.utils;

import co.inventorsoft.warehouse.domain.dto.ManufacturerDto;
import co.inventorsoft.warehouse.domain.dto.ShoeDto;
import co.inventorsoft.warehouse.domain.dto.SizeDto;
import co.inventorsoft.warehouse.domain.entity.ManufacturerEntity;
import co.inventorsoft.warehouse.domain.entity.ShoeEntity;
import co.inventorsoft.warehouse.domain.entity.SizeEntity;
import co.inventorsoft.warehouse.security.dto.JwtResponseDto;
import co.inventorsoft.warehouse.security.entity.RoleEntity;
import co.inventorsoft.warehouse.security.entity.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static co.inventorsoft.warehouse.FillDatabaseService.*;

public class TestDataUtil {
    private TestDataUtil() {
    }

    public static UserEntity generateRoleUserEntity() {
        RoleEntity roleUser1 = RoleEntity.builder()
                .id(1L)
                .name("ROLE_USER")
                .build();

        Collection<RoleEntity> roleEntityCollection1 = List.of(roleUser1);

        UserEntity user1 = UserEntity.builder()
                .id(1L)
                .username("user1")
                .email("user1@email.com")
                .password("user1password")
                .roleEntity(roleEntityCollection1)
                .build();

        return user1;
    }

    public static UserEntity generateRoleAdminEntity() {
        RoleEntity roleAdmin = RoleEntity.builder()
                .id(2L)
                .name("ROLE_ADMIN")
                .build();

        Collection<RoleEntity> roleEntityCollection2 = List.of(roleAdmin);
        UserEntity admin = UserEntity.builder()
                .id(2L)
                .username("admin")
                .email("admin@email.com")
                .password("adminpassword")
                .roleEntity(roleEntityCollection2)
                .build();
        return admin;
    }

    public static ShoeEntity generateShoeEntity() {
        ManufacturerEntity newTestManufacturer = createManufacturer(10L, "Nike");
        SizeEntity newTestSize = createSize(10L, 0, 0);

        return createShoe("New Test Running Shoe", newTestManufacturer, newTestSize);
    }

    public static JwtResponseDto generateJWTToken(UserEntity userRole, MockMvc mockMvc, ObjectMapper objectMapper) throws Exception {
        String roleJson = objectMapper.writeValueAsString(userRole);

        return objectMapper.readValue(mockMvc.perform(
                MockMvcRequestBuilders.post("/api/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(roleJson)

        ).andReturn().getResponse().getContentAsString(), JwtResponseDto.class);
    }

    public static List<ShoeDto> generateShoeDtoListTestData() {
        List<ShoeDto> shoes = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ShoeDto shoe = ShoeDto.builder()
                    .id((long) i + 1)
                    .type("Casual")
                    .manufacturer(generateManufacturerDto())
                    .size(generateSizeDto())
                    .build();

            shoes.add(shoe);
        }

        return shoes;
    }

    public static List<ShoeEntity> generateShoeEntityListTestData() {
        List<ShoeEntity> shoes = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ShoeEntity shoe = ShoeEntity.builder()
                    .id((long) i + 1)
                    .type("Casual")
                    .manufacturer(generateManufacturerEntity())
                    .size(generateSizeEntity())
                    .build();

            shoes.add(shoe);
        }

        return shoes;
    }

    public static ShoeDto generateShoeDto() {
        return ShoeDto.builder()
                .id(1L)
                .type("New Test Running Shoe")
                .manufacturer(generateManufacturerDto())
                .size(generateSizeDto())
                .build();
    }

    public static ManufacturerDto generateManufacturerDto() {

        return ManufacturerDto.builder()
                .id(10L)
                .name("Nike10")
                .build();
    }

    public static SizeDto generateSizeDto() {

        return SizeDto.builder()
                .id((long) (Math.random() * 1000))
                .size((int) (Math.random() * 12) + 6)
                .stock((int) (Math.random() * 50))
                .build();
    }

    public static ManufacturerEntity generateManufacturerEntity() {
        return ManufacturerEntity.builder()
                .id(10L)
                .name("Nike10")
                .build();
    }

    public static SizeEntity generateSizeEntity() {
        return SizeEntity.builder()
                .id(10L)
                .size(40)
                .stock(100)
                .build();
    }
}


