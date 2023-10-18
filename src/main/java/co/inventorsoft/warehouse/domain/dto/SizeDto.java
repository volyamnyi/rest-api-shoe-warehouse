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
public class SizeDto {

    @NotNull
    Long id;

    @NotNull
    private Integer size;

    @NotNull
    private Integer stock;
}
