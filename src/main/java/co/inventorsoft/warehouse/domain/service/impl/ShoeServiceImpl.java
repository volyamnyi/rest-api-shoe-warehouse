package co.inventorsoft.warehouse.domain.service.impl;

import co.inventorsoft.warehouse.domain.dto.ShoeDto;
import co.inventorsoft.warehouse.domain.entity.ManufacturerEntity;
import co.inventorsoft.warehouse.domain.entity.ShoeEntity;
import co.inventorsoft.warehouse.domain.entity.SizeEntity;
import co.inventorsoft.warehouse.domain.mapper.Mapper;
import co.inventorsoft.warehouse.domain.repository.ManufacturerRepository;
import co.inventorsoft.warehouse.domain.repository.ShoeRepository;
import co.inventorsoft.warehouse.domain.repository.SizeRepository;
import co.inventorsoft.warehouse.domain.service.ShoeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
@Transactional
public class ShoeServiceImpl implements ShoeService {

    private ShoeRepository shoeRepository;
    private ManufacturerRepository manufacturerRepository;
    private SizeRepository sizeRepository;

    private Mapper<ShoeEntity, ShoeDto> shoeMapper;

    @Override
    public List<ShoeDto> getAllShoe() {
        return StreamSupport.stream(shoeRepository
                        .findAll()
                        .spliterator(), false)
                .map(shoeMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public ShoeDto getShoeById(Long id) {
        if (!isExist(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return shoeMapper.mapTo(shoeRepository.findById(id).get());
    }

    @Override
    public List<ShoeDto> search(String manufacturer, String type) {
        return StreamSupport.stream(shoeRepository
                        .search(manufacturer, type)
                        .spliterator(), false)
                .map(shoeMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public ShoeDto save(ShoeDto shoeDto) {

        Long manufacturerId = shoeDto.getManufacturer().getId();
        Optional<ManufacturerEntity> manufacturerEntityOptional = manufacturerRepository.findById(manufacturerId);

        if (manufacturerEntityOptional.isEmpty()) {
            throw new NoSuchElementException("There is no nested manufacturer object with such id");
        }

        ShoeEntity shoeEntity = shoeMapper.mapFrom(shoeDto);
        shoeEntity.setManufacturer(manufacturerEntityOptional.get());

        Long sizeId = shoeDto.getSize().getId();
        Optional<SizeEntity> sizeEntityOptional = sizeRepository.findById(sizeId);

        if (sizeEntityOptional.isEmpty()) {
            throw new NoSuchElementException("There is no nested size object with such id");
        }

        SizeEntity sizeEntity = sizeRepository.findById(sizeId).orElseThrow();
        shoeEntity.setSize(sizeEntity);


        return shoeMapper.mapTo(shoeRepository.save(shoeEntity));
    }

    @Override
    public ShoeDto save(Long id, ShoeDto shoeDto) {
        if (!isExist(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return shoeMapper.mapTo(shoeRepository.save(shoeMapper.mapFrom(shoeDto)));
    }

    @Override
    public boolean isExist(Long id) {
        return shoeRepository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        if (!isExist(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        shoeRepository.deleteById(id);
    }

    @Override
    public void delete() {
        shoeRepository.deleteAll();
    }


}
