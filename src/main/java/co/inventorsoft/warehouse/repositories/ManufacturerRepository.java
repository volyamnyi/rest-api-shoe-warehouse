package co.inventorsoft.warehouse.repositories;

import co.inventorsoft.warehouse.domain.entities.ManufacturerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends CrudRepository<ManufacturerEntity, Long> {
}
