package co.inventorsoft.warehouse.services;

import co.inventorsoft.warehouse.domain.entities.ManufacturerEntity;
import co.inventorsoft.warehouse.domain.entities.ShoeEntity;
import co.inventorsoft.warehouse.domain.entities.SizeEntity;
import co.inventorsoft.warehouse.repositories.ManufacturerRepository;
import co.inventorsoft.warehouse.repositories.ShoeRepository;
import co.inventorsoft.warehouse.repositories.SizeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class FillDatabaseService {

    private final ManufacturerRepository manufacturerRepository;
    private final ShoeRepository shoeRepository;
    private final SizeRepository sizeRepository;

    public void generateData() {
        ManufacturerEntity nike = createManufacturer(1L, "Nike");
        ManufacturerEntity adidas = createManufacturer(2L, "Adidas");
        ManufacturerEntity puma = createManufacturer(3L, "Puma");

        SizeEntity size40 = createSize(1L, 40, 100);
        SizeEntity size41 = createSize(2L, 41, 150);
        SizeEntity size42 = createSize(3L, 42, 75);

        ShoeEntity runningShoe = createShoe("Running Shoe", nike, size40);
        ShoeEntity runningShoe2 = createShoe("Running Shoe", nike, size40);
        ShoeEntity casualShoe = createShoe("Casual Shoe", adidas, size41);
        ShoeEntity sportsShoe = createShoe("Sports Shoe", puma, size42);

        saveEntities(runningShoe, casualShoe, sportsShoe, runningShoe2);
        saveEntities(nike, adidas, puma);
        saveEntities(size40, size41, size42);

    }

    private ManufacturerEntity createManufacturer(long id, String name) {
        return ManufacturerEntity.builder()
                .id(id)
                .name(name)
                .build();
    }

    private SizeEntity createSize(long id, int size, int stock) {
        return SizeEntity.builder()
                .id(id)
                .size(size)
                .stock(stock)
                .build();
    }

    private ShoeEntity createShoe(String type, ManufacturerEntity manufacturer, SizeEntity size) {
        return ShoeEntity.builder()
                .type(type)
                .manufacturer(manufacturer)
                .size(size)
                .build();
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
}
