package co.inventorsoft.warehouse.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SizeDto {

    Long id;
    private Integer size;
    private Integer stock;
}
