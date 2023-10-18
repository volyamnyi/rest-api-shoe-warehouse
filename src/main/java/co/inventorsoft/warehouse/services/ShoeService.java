package co.inventorsoft.warehouse.services;

import co.inventorsoft.warehouse.domain.entities.ShoeEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShoeService {

    List<ShoeEntity> getAllShoe();

    ShoeEntity getShoeById(Long id);

    List<ShoeEntity> search(String manufacturer, String type);

    ShoeEntity save(ShoeEntity shoeEntity);

    boolean isExist(Long id);


    void delete(ShoeEntity shoeEntity);

    void delete();
}
