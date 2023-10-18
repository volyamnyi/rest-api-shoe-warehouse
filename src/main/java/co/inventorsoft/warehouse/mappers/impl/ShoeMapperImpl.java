package co.inventorsoft.warehouse.mappers.impl;

import co.inventorsoft.warehouse.domain.dto.ShoeDto;
import co.inventorsoft.warehouse.domain.entities.ShoeEntity;
import co.inventorsoft.warehouse.mappers.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ShoeMapperImpl implements Mapper<ShoeEntity, ShoeDto> {

    private ModelMapper modelMapper;

    @Override
    public ShoeDto mapTo(ShoeEntity shoeEntity) {
        return modelMapper.map(shoeEntity, ShoeDto.class);
    }

    @Override
    public ShoeEntity mapFrom(ShoeDto shoeDto) {
        return modelMapper.map(shoeDto, ShoeEntity.class);
    }
}
