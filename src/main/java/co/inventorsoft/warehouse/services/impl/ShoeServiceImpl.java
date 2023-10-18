package co.inventorsoft.warehouse.services.impl;

import co.inventorsoft.warehouse.domain.entities.ShoeEntity;
import co.inventorsoft.warehouse.repositories.ShoeRepository;
import co.inventorsoft.warehouse.services.ShoeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShoeServiceImpl implements ShoeService {

    private ShoeRepository shoeRepository;

    @Override
    public List<ShoeEntity> getAllShoe() {
        return (List<ShoeEntity>) shoeRepository.findAll();
    }

    @Override
    public ShoeEntity getShoeById(Long id) {
        return shoeRepository.findById(id).get();
    }

    @Override
    public List<ShoeEntity> search(String manufacturer, String type) {
        return (List<ShoeEntity>) shoeRepository.search(manufacturer, type);
    }

    @Override
    public ShoeEntity save(ShoeEntity shoeEntity) {
        return shoeRepository.save(shoeEntity);
    }

    @Override
    public boolean isExist(Long id) {
        return shoeRepository.existsById(id);
    }

    @Override
    public void delete(ShoeEntity shoeEntity) {
        shoeRepository.delete(shoeEntity);
    }

    @Override
    public void delete() {
        shoeRepository.deleteAll();
    }


}
