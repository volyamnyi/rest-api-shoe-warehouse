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
public class ManufacturerDto {

    @NotNull
    Long id;

    @NotNull
    private String name;
}
