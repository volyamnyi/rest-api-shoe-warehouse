package co.inventorsoft.warehouse.domain.service;

import co.inventorsoft.warehouse.domain.dto.ShoeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShoeService {

    List<ShoeDto> getAllShoe();

    ShoeDto getShoeById(Long id);

    List<ShoeDto> search(String manufacturer, String type);

    ShoeDto save(ShoeDto shoeDto);

    ShoeDto save(Long id, ShoeDto shoeDto);

    boolean isExist(Long id);


    void delete(Long id);

    void delete();
}
