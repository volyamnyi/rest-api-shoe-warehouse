package co.inventorsoft.warehouse.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoeDto {
    Long id;

    @NotNull
    private String type;
    private ManufacturerDto manufacturer;
    private SizeDto size;

}
