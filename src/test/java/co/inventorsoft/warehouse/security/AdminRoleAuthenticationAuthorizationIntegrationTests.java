package co.inventorsoft.warehouse.security;

import co.inventorsoft.warehouse.domain.entity.ShoeEntity;
import co.inventorsoft.warehouse.security.dto.JwtResponseDto;
import co.inventorsoft.warehouse.security.entity.UserEntity;
import co.inventorsoft.warehouse.utils.TestDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static co.inventorsoft.warehouse.utils.TestDataUtil.generateJWTToken;

@SpringBootTest
@AutoConfigureMockMvc
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AdminRoleAuthenticationAuthorizationIntegrationTests {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public AdminRoleAuthenticationAuthorizationIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testThatRoleAdminAuthenticationReturnsHttp200() throws Exception {
        UserEntity roleUser = TestDataUtil.generateRoleAdminEntity();

        String roleUserJson = objectMapper.writeValueAsString(roleUser);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(roleUserJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testThatUserWithRoleAdminCanAddNewShoe() throws Exception {
        UserEntity roleUser = TestDataUtil.generateRoleAdminEntity();

        JwtResponseDto token = generateJWTToken(roleUser, mockMvc, objectMapper);

        String shoeJson = objectMapper.writeValueAsString(TestDataUtil.generateShoeEntity());

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/shoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token.getToken())
                        .content(shoeJson)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testThatUserWithRoleAdminCanUpdateShoeById() throws Exception {
        UserEntity roleUser = TestDataUtil.generateRoleAdminEntity();

        JwtResponseDto token = generateJWTToken(roleUser, mockMvc, objectMapper);

        long idFoUpdate = 3L;

        ShoeEntity updatedShoeEntity = objectMapper.readValue(mockMvc.perform(
                MockMvcRequestBuilders.get("/api/shoes/" + idFoUpdate)
        ).andReturn().getResponse().getContentAsString(), ShoeEntity.class);

        updatedShoeEntity.setType("Updated Type");

        String shoeJson = objectMapper.writeValueAsString(updatedShoeEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/shoes/" + idFoUpdate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token.getToken())
                        .content(shoeJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatUserWithRoleAdminCanDeleteShoeById() throws Exception {
        UserEntity roleUser = TestDataUtil.generateRoleAdminEntity();

        JwtResponseDto token = generateJWTToken(roleUser, mockMvc, objectMapper);

        long idForDelete = 1;

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/shoes/" + idForDelete)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token.getToken())
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatUserWithRoleUserCanDeleteAllShoes() throws Exception {
        UserEntity roleUser = TestDataUtil.generateRoleAdminEntity();

        JwtResponseDto token = generateJWTToken(roleUser, mockMvc, objectMapper);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/shoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token.getToken())
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
