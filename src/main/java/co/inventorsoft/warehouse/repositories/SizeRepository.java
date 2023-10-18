package co.inventorsoft.warehouse.repositories;

import co.inventorsoft.warehouse.domain.entities.SizeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends CrudRepository<SizeEntity, Long> {
}
