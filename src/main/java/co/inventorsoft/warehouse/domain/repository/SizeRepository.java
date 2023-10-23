package co.inventorsoft.warehouse.domain.repository;

import co.inventorsoft.warehouse.domain.entity.SizeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends CrudRepository<SizeEntity, Long> {
}
