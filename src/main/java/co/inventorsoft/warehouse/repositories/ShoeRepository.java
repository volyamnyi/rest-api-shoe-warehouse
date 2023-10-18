package co.inventorsoft.warehouse.repositories;

import co.inventorsoft.warehouse.domain.entities.ShoeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShoeRepository extends CrudRepository<ShoeEntity, Long> {

    @Query("SELECT s FROM ShoeEntity s JOIN s.manufacturer m WHERE m.name = :manufacturer OR s.type = :type")
    Iterable<ShoeEntity> search(String manufacturer, String type);
}
