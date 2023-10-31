package co.inventorsoft.warehouse.domain.controller;

import co.inventorsoft.warehouse.domain.dto.ShoeDto;
import co.inventorsoft.warehouse.domain.service.ShoeService;
import co.inventorsoft.warehouse.utils.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShoeControllerUnitTests {

    @Mock
    private ShoeService shoeService;

    @InjectMocks
    private ShoeController shoeController;

    @Test
    void testThatGetAllShoesReturnsAllShoes() {
        List<ShoeDto> mockShoes = TestDataUtil.generateShoeDtoListTestData();
        when(shoeService.getAllShoe()).thenReturn(mockShoes);

        List<ShoeDto> result = shoeController.getAllShoes();

        assertEquals(mockShoes, result);
        verify(shoeService).getAllShoe();
    }

    @Test
    void testThatGetShoeByIdReturnsShoeForValidId() {
        long validId = 1L;
        ShoeDto mockShoe = new ShoeDto();
        when(shoeService.getShoeById(validId)).thenReturn(mockShoe);

        ShoeDto result = shoeController.getShoeById(validId);

        assertEquals(mockShoe, result);
        verify(shoeService).getShoeById(validId);
    }

    @Test
    void testThatGetShoeByIdThrowsExceptionForInvalidId() {
        long invalidId = 100L;
        when(shoeService.getShoeById(invalidId)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        assertThrows(ResponseStatusException.class, () -> shoeController.getShoeById(invalidId));
        verify(shoeService).getShoeById(invalidId);
    }

    @Test
    void testThatSearchReturnsFilteredShoes() {
        String manufacturer = "Nike";
        String type = "Running";

        List<ShoeDto> mockFilteredShoes = TestDataUtil.generateShoeDtoListTestData();
        when(shoeService.search(manufacturer, type)).thenReturn(mockFilteredShoes);

        List<ShoeDto> result = shoeController.search(manufacturer, type);

        assertEquals(mockFilteredShoes, result);
        verify(shoeService).search(manufacturer, type);
    }

    @Test
    void testThatCreateNewShoeReturnsCreatedShoe() {
        ShoeDto shoeDto = TestDataUtil.generateShoeDto();
        when(shoeService.save(shoeDto)).thenReturn(shoeDto);

        ShoeDto result = shoeController.createNewShoe(shoeDto);

        assertEquals(shoeDto, result);
        verify(shoeService).save(shoeDto);
    }

    @Test
    void testThatUpdateShoeReturnsUpdatedShoeForValidId() {
        long validId = 1L;
        ShoeDto shoeDto = TestDataUtil.generateShoeDto();
        when(shoeService.save(validId, shoeDto)).thenReturn(shoeDto);

        ShoeDto result = shoeController.updateShoe(validId, shoeDto);

        assertEquals(shoeDto, result);
        verify(shoeService).save(validId, shoeDto);
    }

    @Test
    void testThatUpdateShoeThrowsExceptionForInvalidId() {
        long invalidId = 100L;
        ShoeDto shoeDto = TestDataUtil.generateShoeDto();
        when(shoeService.save(invalidId, shoeDto)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        assertThrows(ResponseStatusException.class, () -> shoeController.updateShoe(invalidId, shoeDto));
        verify(shoeService).save(invalidId, shoeDto);
    }

    @Test
    void testThatDeleteShoeByIdDeletesShoeForValidId() {
        long validId = 1L;
        shoeController.deleteShoeById(validId);
        verify(shoeService).delete(validId);
    }

    @Test
    void testThatDeleteAllShoesDeletesAllShoes() {
        shoeController.deleteAllShoes();
        verify(shoeService).delete();
    }
}