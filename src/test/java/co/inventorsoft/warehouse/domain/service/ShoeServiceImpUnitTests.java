package co.inventorsoft.warehouse.domain.service;

import co.inventorsoft.warehouse.domain.dto.ShoeDto;
import co.inventorsoft.warehouse.domain.entity.ManufacturerEntity;
import co.inventorsoft.warehouse.domain.entity.ShoeEntity;
import co.inventorsoft.warehouse.domain.entity.SizeEntity;
import co.inventorsoft.warehouse.domain.mapper.Mapper;
import co.inventorsoft.warehouse.domain.repository.ManufacturerRepository;
import co.inventorsoft.warehouse.domain.repository.ShoeRepository;
import co.inventorsoft.warehouse.domain.repository.SizeRepository;
import co.inventorsoft.warehouse.utils.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShoeServiceImpUnitTests {

    @Mock
    private ShoeRepository shoeRepository;

    @Mock
    private ManufacturerRepository manufacturerRepository;

    @Mock
    private SizeRepository sizeRepository;

    @Mock
    private Mapper<ShoeEntity, ShoeDto> shoeMapper;

    @InjectMocks
    private ShoeServiceImpl shoeServiceImpl;

    @Test
    void testThatGetAllShoeReturnsListOfShoeDto() {
        when(shoeRepository.findAll()).thenReturn(TestDataUtil.generateShoeEntityListTestData());
        when(shoeMapper.mapTo(any(ShoeEntity.class))).thenReturn(TestDataUtil.generateShoeDto());
        List<ShoeDto> result = shoeServiceImpl.getAllShoe();
        assertNotNull(result);
    }

    @Test
    void testThatGetShoeByIdReturnsShoeDtoIfExist() {
        Long existingId = 1L;
        ShoeEntity shoeEntity = TestDataUtil.generateShoeEntity();

        when(shoeServiceImpl.isExist(1L)).thenReturn(true);
        when(shoeRepository.findById(existingId)).thenReturn(Optional.of(shoeEntity));
        when(shoeServiceImpl.getShoeById(existingId)).thenReturn(TestDataUtil.generateShoeDto());

        ShoeDto result = shoeServiceImpl.getShoeById(existingId);
        assertNotNull(result);
    }

    @Test
    void testThatGetShoeByIdThrowsResponseStatusException404IfNotExist() {
        Long nonExistingId = 10L;
        assertThrows(ResponseStatusException.class, () -> shoeServiceImpl.getShoeById(nonExistingId));
    }

    @Test
    void testThatSearchReturnsExpectedCountOfShoes() {
        String manufacturer = "Nike";
        String type = "New Test Running Shoe";


        ShoeEntity shoeEntity1 = TestDataUtil.generateShoeEntity();
        ShoeDto shoeDto1 = TestDataUtil.generateShoeDto();

        ShoeEntity shoeEntity2 = TestDataUtil.generateShoeEntity();
        ShoeDto shoeDto2 = TestDataUtil.generateShoeDto();


        when(shoeRepository.search(manufacturer, type)).thenReturn(List.of(shoeEntity1, shoeEntity2));
        when(shoeMapper.mapTo(shoeEntity1)).thenReturn(shoeDto1);
        when(shoeMapper.mapTo(shoeEntity2)).thenReturn(shoeDto2);


        List<ShoeDto> result = shoeServiceImpl.search(manufacturer, type);

        assertEquals(2, result.size());
    }

    @Test
    void testThatSaveSuccessfullySaves() {
        ShoeDto shoeDto = TestDataUtil.generateShoeDto();
        ShoeEntity shoeEntity = TestDataUtil.generateShoeEntity();

        ManufacturerEntity manufacturerEntity = TestDataUtil.generateManufacturerEntity();
        SizeEntity sizeEntity = TestDataUtil.generateSizeEntity();

        when(manufacturerRepository.findById(shoeDto.getManufacturer().getId())).thenReturn(Optional.of(manufacturerEntity));
        when(sizeRepository.findById(shoeDto.getSize().getId())).thenReturn(Optional.of(sizeEntity));

        when(shoeMapper.mapFrom(shoeDto)).thenReturn(shoeEntity);
        when(shoeRepository.save(any(ShoeEntity.class))).thenReturn(shoeEntity);
        when(shoeMapper.mapTo(any(ShoeEntity.class))).thenReturn(shoeDto);

        ShoeDto result = shoeServiceImpl.save(shoeDto);

        assertNotNull(result);
    }

    @Test
    void testThatIsExistReturnsTrueWhenExistingIdProvided() {
        Long existingId = 1L;
        when(shoeRepository.existsById(existingId)).thenReturn(true);
        boolean result = shoeServiceImpl.isExist(existingId);
        assertTrue(result);
    }

    @Test
    void testThatIsExistReturnsFalseWhenNonExistingIdProvided() {
        Long nonExistingId = 10L;
        when(shoeRepository.existsById(nonExistingId)).thenReturn(false);
        boolean result = shoeServiceImpl.isExist(nonExistingId);
        assertFalse(result);
    }

    @Test
    void testThatDeleteDeletesShoeByExistingId() {
        Long existingId = 1L;
        when(shoeRepository.existsById(existingId)).thenReturn(true);
        assertDoesNotThrow(() -> shoeServiceImpl.delete(existingId));
        verify(shoeRepository, times(1)).deleteById(existingId);
    }

    @Test
    void testThatDeleteDoesNotDeleteShoeByNonExistingId() {
        Long nonExistingId = 10L;
        when(shoeRepository.existsById(nonExistingId)).thenReturn(false);
        assertThrows(ResponseStatusException.class, () -> shoeServiceImpl.delete(nonExistingId));
        verify(shoeRepository, never()).deleteById(nonExistingId);
    }
}