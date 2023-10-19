package co.inventorsoft.warehouse.mapper.impl;

import co.inventorsoft.warehouse.domain.dto.ShoeDto;
import co.inventorsoft.warehouse.domain.entity.ShoeEntity;
import co.inventorsoft.warehouse.mapper.Mapper;
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
