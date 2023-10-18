package co.inventorsoft.warehouse.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "shoes")
public class ShoeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shoe_id_seq")
    @Column(unique = true)
    Long id;
    private String type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manufacturer_id")
    private ManufacturerEntity manufacturer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "size_id")
    private SizeEntity size;

}
