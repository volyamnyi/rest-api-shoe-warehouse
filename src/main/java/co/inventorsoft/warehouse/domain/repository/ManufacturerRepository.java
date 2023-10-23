package co.inventorsoft.warehouse.domain.repository;

import co.inventorsoft.warehouse.domain.entity.ManufacturerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends CrudRepository<ManufacturerEntity, Long> {
}
