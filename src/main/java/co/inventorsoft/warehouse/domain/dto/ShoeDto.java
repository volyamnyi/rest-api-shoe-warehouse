package co.inventorsoft.warehouse.domain.dto;

import jakarta.validation.Valid;
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

    @NotNull
    @Valid
    private ManufacturerDto manufacturer;

    @NotNull
    @Valid
    private SizeDto size;

}
